package demo;
/**
 * Task List
 * (simulation of a smartphone application)
 *
 * Author: Sharon Curtis
 * Date: January 2011
 *
 * You do NOT need to read the code in this file,
 * nor do you need to know how it works.
 */
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.font.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.util.*;

public class TaskListGUI extends JFrame implements ActionListener {

    public boolean filesEnabled = false;  // change this to true, to enable
    // loading and saving data from/to a file

    public static void main(String args[]) {
        // sets up an instance of this class
        TaskListGUI app = new TaskListGUI();
    }

    public TaskListGUI() {
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(340, 640);
        setTitle("Task List");
        setIconImage(getLogo(16));
        fc = new JFileChooser(".");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dataSetUp();
        setUpGUI();
        setVisible(true);
    }
// ------------------- GUI set up ---------------//
    JLabel blank;  // this gets put in place of the add text box, when the text box isn't visible
    JButton addOption, saveOption;
    JPanel titlePanel, menuPanel, mainPanel, listPanel, addPanel;
    JScrollPane scrollPane;
    JTextField addBox;
    JFileChooser fc;
    Color importantColour = Color.red;
    Color pendingColour = new Color(0, 82, 255);
    Color taskColour = new Color(185, 185, 185);
    Color borderColour = new Color(59, 59, 59);

    public void setUpGUI() {

        // set up the title and logo
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.black);
        titlePanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
        JLabel logo = new JLabel(new ImageIcon(getLogo(40)));
        JPanel wrapper = new JPanel();
        wrapper.add(logo);
        wrapper.setBackground(Color.black);
        wrapper.setBorder(new MatteBorder(2, 2, 12, 2, Color.black));
        titlePanel.add(wrapper, BorderLayout.WEST);
        JLabel heading = new JLabel("Task List", SwingConstants.CENTER);
        heading.setFont(new Font("Verdana", 0, 24));
        heading.setForeground(Color.white);
        heading.setBorder(new LineBorder(Color.black, 14));
        titlePanel.add(heading, BorderLayout.CENTER);
        getContentPane().add(titlePanel, BorderLayout.NORTH);


        // set up the area with an add box
        addPanel = new JPanel(new GridLayout(1, 1));
        addBox = new JTextField();
        addBox.setActionCommand("additem");
        addPanel.setBackground(Color.black);
        addBox.setFont(new Font("Verdana", 0, 16));
        blank = new JLabel(" ");
        blank.setBackground(Color.black);
        addPanel.add(blank);

        // set up the panel of menu "buttons" at the bottom
        menuPanel = new JPanel(new GridLayout(1, 2));
        menuPanel.setBackground(new Color(239, 239, 239));

        addOption = new JButton(new ImageIcon(getMenuImage(158, 66, "Add Task")));
        addOption.setBorder(new LineBorder(new Color(180, 180, 180), 2));
        addOption.setBackground(new Color(239, 239, 239));
        addOption.setActionCommand("add");
        addOption.addActionListener(this);
        menuPanel.add(addOption);

        saveOption = new JButton(new ImageIcon(getMenuImage(158, 66, "Save All")));
        saveOption.setBorder(new LineBorder(new Color(180, 180, 180), 2));
        saveOption.setBackground(new Color(239, 239, 239));
        saveOption.setActionCommand("savecancel");
        saveOption.addActionListener(this);
        menuPanel.add(saveOption);

        getContentPane().add(menuPanel, java.awt.BorderLayout.SOUTH);

        // set up the main area
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.black);
        mainPanel.add(addPanel, BorderLayout.NORTH);
        updateListView();
        scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

    }

    public void updateListView() {
        // Updates the GUI components for the list.
        // Called initially when the GUI is set-up the first time, and
        // afterwards, is called every time the GUI is redrawn.

        int cells;
        if (items != null) {
            cells = items.size();
        } else {
            cells = 0;
        }
        if (cells > 10) {
            listPanel = new JPanel(new GridLayout(cells, 1));
        } else {
            listPanel = new JPanel(new GridLayout(10, 1));
        }
        listPanel.setBackground(Color.black);

        for (int i = 0; i < cells; i++) {
            JPanel cellPanel = new JPanel(new BorderLayout());
            cellPanel.setBackground(taskColour);
            cellPanel.setBorder(new MatteBorder(2, 0, 2, 0, borderColour));
            JButton clickable = new JButton(new ImageIcon(getItemImage(getWidth() - 15, items.get(i), i < firstListSize)));
            clickable.setBackground(taskColour);
            clickable.setBorder(BorderFactory.createEmptyBorder());
            clickable.setActionCommand("click" + i);
            clickable.addActionListener(this);
            cellPanel.add(clickable, BorderLayout.WEST);
            listPanel.add(cellPanel);
        }
        for (int i = cells; i < 10; i++) {
            JPanel cellPanel = new JPanel(new BorderLayout());
            cellPanel.setBackground(Color.black);
            JLabel placeholder = new JLabel(" ");
            placeholder.setFont(new Font("Verdana", 0, 14));
            cellPanel.add(placeholder);
            listPanel.add(cellPanel);
        }
    }
// --------------- setting up connections to the lists manager ---------------//
    ListsManager lists; // the connection to the lists manager
    private boolean addingNew = false; // to remember if we're currently adding a new item
    int positionSelected = -1; // for remembering which element was last selected
    ArrayList<String> items; // will hold the items held by the lists manager
    int firstListSize;

    public void dataSetUp() {
        lists = new ListsManager(this);

        if (filesEnabled) {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                fc.setCurrentDirectory(f.getParentFile());
                loadFile(f);
            }
        }

        // grabbing the data from the lists manager
        grabData();
    }

    private void grabData() {

        firstListSize = 0; // default value in case of no lists!

        items = new ArrayList<String>();
        if (lists.highPriority != null) {
            firstListSize = lists.highPriority.size();
            for (String s : lists.highPriority) {
                items.add(s);
            }

        }
        if (lists.lowPriority != null) {
            for (String s : lists.lowPriority) {
                items.add(s);
            }
        }
    }

// --------------- code for handling events ---------------//
    public void actionPerformed(ActionEvent e) {
        String which = e.getActionCommand();

        if (which.startsWith("click")) {
            // System.out.println("click from "+Integer.parseInt(which.substring(5)));
            clickedItem(Integer.parseInt(which.substring(5)));
        } else if (which.equals("add") && !addingNew) {
            // System.out.println("click for add");
            startAdding();
        } else if (which.equals("additem") || (which.equals("add") && addingNew)) {
            // System.out.println("click for addItem");
            addItem();
        } else if (which.equals("savecancel") && addingNew) {
            // System.out.println("click for save/cancel");
            cancelAdd();
        } else if (which.equals("savecancel") && !addingNew) {
            // System.out.println("click for save/cancel");
            saveLists();
        }
    }

    public void redraw() {
        updateListView();
        scrollPane.setViewportView(listPanel);
        repaint();
    }

    private void startAdding() {
        // readies the interface to be in an "adding new item" state
        addingNew = true;
        saveOption.setIcon(new ImageIcon(getMenuImage(158, 66, "Cancel")));
        addPanel.remove(blank);
        addBox.setText("");
        addBox.addActionListener(this);
        addPanel.add(addBox);
        addBox.requestFocus();
        validate();
        repaint();
    }

    private void addItem() {
        String item = addBox.getText();
        if (item.length() > 0) {
            lists.addTask(item);
            grabData();
        }
        addBox.setText("");
        cancelAdd();
    }

    private void cancelAdd() {
        // cancels the adding of a new element that the user was in the middle of
        addingNew = false;
        saveOption.setIcon(new ImageIcon(getMenuImage(158, 66, "Save All")));
        addPanel.remove(addBox);
        addBox.removeActionListener(this);
        addPanel.add(blank);
        redraw();
    }

    private void saveLists() {
        String fileContents = lists.toString();
        System.out.println("\n[Contents of lists to save to file]\n"
                + fileContents);
        if (filesEnabled) {
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                fc.setCurrentDirectory(f.getParentFile());
                saveFile(fileContents, f);
            }
        }
    }

    private void clickedItem(int i) {
        String task = items.get(i);
        TaskClickDialogue tcd = new TaskClickDialogue(this, task, (i < firstListSize));
        String chosen = tcd.showDialogue();
        // System.out.println("return value is "+chosen);
        if (chosen.equals("done")) {
            if (i < firstListSize) {
                lists.removeHighPriorityTask(i);
            } else {
                lists.removeLowPriorityTask(i - firstListSize);
            }
            grabData();
            redraw();
        } else if (chosen.equals("change")) {
            if (i < firstListSize) {
                lists.changePriority(true, i);
            } else {
                lists.changePriority(false, i - firstListSize);
            }
            grabData();
            redraw();
        } else if (chosen.equals("promote")) {
            if (i < firstListSize) {
                lists.promote(i);
            }
            grabData();
            redraw();
        }
        // else do nothing if the cancel option is selected
    }

// --------------- file input and output methods ---------------//
    public void loadFile(File f) {

        try {
            lists.initialiseLists();
            if (lists.highPriority != null
                    && lists.lowPriority != null) {
                BufferedReader inf = new BufferedReader(new FileReader(f));
                boolean important = true;
                String line = "";
                while ((line = inf.readLine()) != null) {
                    line = line.trim();
                    if (lowPriorityLine(line)) {
                        important = false;
                    } else if (!highPriorityLine(line) && line.length() > 0) {
                        if (important) {
                            lists.highPriority.add(line);
                        } else {
                            lists.lowPriority.add(line);
                        }
                    }
                }
                inf.close();
            }
        } catch (Exception e) {
            System.out.println("Cannot load the file \""
                    + f.getPath() + "\":");
            System.out.println(e.toString());
        }
    }

    public boolean highPriorityLine(String line) {
        String s = line.toLowerCase();
        return s.contains("tasks")
                && ((s.contains("high") && s.contains("priority"))
                || s.contains("important"));
    }

    public boolean lowPriorityLine(String line) {
        String s = line.toLowerCase();
        return (s.contains("priority") && s.contains("tasks")
                && s.contains("low"));
    }

    public void saveFile(String contents, File f) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(f));
            out.println(contents);
            out.close();
        } catch (Exception e) {
            System.out.println("Problem with saving to file \"" + f.getPath() + "\".");
            System.out.println(e.toString());
        }
    }
// --------------- various graphics drawing methods ---------------//
    public int fontSize = 14;

    /** Returns a small image for a logo. */
    public BufferedImage getLogo(int width) {

        // set up the image
        BufferedImage bi = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.createGraphics();

        g.setPaint(Color.black);
        Rectangle2D.Double bg = new Rectangle2D.Double(0.0, 0.0, (double) width, (double) width);
        g.fill(bg);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int offsetX = 40;
        g.scale(width / 80.0, width / 80.0);
        // now to draw the little coloured box to the left
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        Color lightI = importantColour;
        Color midI = lightI.darker().darker();
        Color darkerI = midI.darker().darker();
        Color lightP = pendingColour;
        Color midP = lightP.darker().darker();
        Color darkerP = midP.darker().darker();

        RoundRectangle2D.Float tab = new RoundRectangle2D.Float(11.0f, 1.0f, 27.0f, 38.0f, 4.0f, 4.0f);
        g.setPaint(new GradientPaint(20.0f, 1.0f, lightP, 20.0f, 38.0f, midP));
        g.fill(tab);
        g.setPaint(darkerP);
        g.draw(tab);

        tab = new RoundRectangle2D.Float(41.0f, 1.0f, 27.0f, 38.0f, 4.0f, 4.0f);
        g.setPaint(new GradientPaint(40.0f, 1.0f, lightI, 40.0f, 38.0f, midI));
        g.fill(tab);
        g.setPaint(darkerI);
        g.draw(tab);

        tab = new RoundRectangle2D.Float(11.0f, 41.0f, 27.0f, 38.0f, 4.0f, 4.0f);
        g.setPaint(new GradientPaint(20.0f, 41.0f, lightI, 20.0f, 78.0f, midI));
        g.fill(tab);
        g.setPaint(darkerI);
        g.draw(tab);

        tab = new RoundRectangle2D.Float(40.0f, 41.0f, 27.0f, 38.0f, 4.0f, 4.0f);
        g.setPaint(new GradientPaint(40.0f, 41.0f, lightP, 40.0f, 79.0f, midP));
        g.fill(tab);
        g.setPaint(darkerP);
        g.draw(tab);

        return bi;
    }

    public BufferedImage getMenuImage(int width, int height, String option) {

        // set up the image
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.createGraphics();

        // set a white background
        g.setPaint(Color.white);
        Rectangle2D.Double bg = new Rectangle2D.Double(0.0, 0.0, (double) width, (double) height);
        g.fill(bg);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        Font f = new Font("Trebuchet", Font.PLAIN, 14);
        g.setFont(f);
        g.setPaint(Color.black);
        GlyphVector gl = f.createGlyphVector(g.getFontRenderContext(), option);
        Rectangle2D box = gl.getVisualBounds();
        int x = (int) ((width - box.getWidth()) / 2);
        int y = height - 7;
        g.drawString(option, x, y);

        // now to draw the little picture
        double cx = width / 2.0;
        double cy = (y - 14) / 2.0;
        if (option.contains("Add")) {
            double thickness = 4.0;
            double length = 14.0;
            Rectangle2D.Double r;
            r = new Rectangle2D.Double(cx - thickness / 2.0, cy - length / 2.0,
                    thickness, length);
            g.fill(r);
            r = new Rectangle2D.Double(cx - length / 2.0, cy - thickness / 2.0,
                    length, thickness);
            g.fill(r);
        } else if (option.contains("Cancel")) {
            double little = 4.0;
            double large = 6.5;
            Path2D.Double p = new Path2D.Double();
            p.moveTo(cx - little, cy - large);
            p.lineTo(cx + large, cy + little);
            p.lineTo(cx + little, cy + large);
            p.lineTo(cx - large, cy - little);
            p.lineTo(cx - little, cy - large);
            g.fill(p);
            p = new Path2D.Double();
            p.moveTo(cx + little, cy - large);
            p.lineTo(cx + large, cy - little);
            p.lineTo(cx - little, cy + large);
            p.lineTo(cx - large, cy + little);
            p.lineTo(cx + little, cy - large);
            g.fill(p);
        } else if (option.contains("Save")) {
            double w = 16.0;
            double h = 20.0;
            Rectangle2D.Double r;
            r = new Rectangle2D.Double(cx - w / 2.0, cy - h / 2.0, w, h);
            g.draw(r);
            Line2D.Double line;
            double gap = 3.0;
            double len = 8.0;
            line = new Line2D.Double(cx - len / 2.0, cy, cx + len / 2.0, cy);
            g.draw(line);
            line = new Line2D.Double(cx - len / 2.0, cy + gap, cx + len / 2.0, cy + gap);
            g.draw(line);
            line = new Line2D.Double(cx - len / 2.0, cy + 2 * gap, cx + len / 2.0, cy + 2 * gap);
            g.draw(line);
            line = new Line2D.Double(cx - len / 2.0, cy - gap, cx + len / 2.0, cy - gap);
            g.draw(line);
            line = new Line2D.Double(cx - len / 2.0, cy - 2 * gap, cx + len / 2.0, cy - 2 * gap);
            g.draw(line);
        }
        return bi;
    }

    public BufferedImage getItemImage(int width, String task, boolean important) {


        // set up the image
        int height = 40;
        int rightExtra = 800;
        BufferedImage bi = new BufferedImage(width + rightExtra, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.createGraphics();

        // set the background
        Color bgc = taskColour;
        if (important) {
            bgc = bgc.brighter();
        }
        g.setPaint(new GradientPaint(20.0f, 0.0f, bgc,
                20.0f, (float) height, bgc.darker().darker()));

        Rectangle2D.Double bg = new Rectangle2D.Double(0.0, 0.0, (double) (width + rightExtra), (double) height);
        g.fill(bg);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int offsetX = 40;

        Font f = new Font("Verdana", Font.BOLD, fontSize);
        g.setFont(f);
        g.setPaint(Color.black);
        String toDisplay = task;
        GlyphVector gl = f.createGlyphVector(g.getFontRenderContext(), toDisplay);
        Rectangle2D box = gl.getVisualBounds();
        if (box.getWidth() + offsetX + 3 > width) {  // too wide, oops!
            while (box.getWidth() + offsetX + 3 > width) {
                toDisplay = toDisplay.substring(0, toDisplay.length() - 1);
                gl = f.createGlyphVector(g.getFontRenderContext(), toDisplay + " (...)");
                box = gl.getVisualBounds();
            }
            toDisplay = toDisplay + " (...)";
        }

        int y = (height + fontSize) / 2;
        g.drawString(toDisplay, offsetX + 3, y);

        // now to draw the little coloured box to the left
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        Color lightC;
        if (important) {
            lightC = importantColour;
        } else {
            lightC = pendingColour;
        }
        Color midC = lightC.darker().darker();
        Color darkerC = midC.darker();
        if (important) {
            darkerC = darkerC.darker();
        }
        RoundRectangle2D.Float tab = new RoundRectangle2D.Float(1.0f, 1.0f, offsetX - 13.0f, height - 2.0f, 4.0f, 4.0f);
        g.setPaint(new GradientPaint(20.0f, 0.0f, lightC, 20.0f, (float) height, midC));
        g.fill(tab);
        g.setPaint(darkerC);
        g.draw(tab);

        return bi;
    }

    class TaskClickDialogue extends JDialog implements ActionListener {

        public String clickedOn;

        public TaskClickDialogue(Frame frame, String task, boolean importantTask) {

            super(frame, "Choose option for task", true);

            clickedOn = "cancel";

            setBackground(Color.black);

            Font f = new Font("Verdana", Font.BOLD, 16);
            JLabel top = new JLabel("Choose option for task", SwingConstants.LEFT);
            top.setOpaque(true);
            top.setFont(f);
            top.setBackground(Color.black);
            top.setForeground(Color.white);
            top.setBorder(new MatteBorder(10, 10, 5, 10, Color.black));

            JLabel top2 = new JLabel("\"" + task + "\":", SwingConstants.LEFT);
            top2.setOpaque(true);
            top2.setFont(f);
            top2.setBackground(Color.black);
            top2.setForeground(Color.white);
            top2.setBorder(new MatteBorder(5, 10, 10, 10, Color.black));
            JPanel topPanel = new JPanel(new GridLayout(2, 1));
            topPanel.add(top);
            topPanel.add(top2);
            getContentPane().add(topPanel, BorderLayout.NORTH);

            JPanel buttonPane;
            if (importantTask) {
                buttonPane = new JPanel(new GridLayout(4, 1));
            } else {
                buttonPane = new JPanel(new GridLayout(3, 1));
            }


            String[] labels = {"Done! (remove task)", "Change priority", "Move higher", "Cancel"};
            String[] commands = {"done", "change", "promote", "cancel"};
            JButton[] buttons = new JButton[4];
            JPanel[] buttonPanels = new JPanel[4];

            for (int i = 0; i < buttons.length; i++) {
                buttonPanels[i] = new JPanel(new FlowLayout());
                buttonPanels[i].setBackground(Color.black);
                buttons[i] = new JButton(new ImageIcon(getButtonImage(labels, i)));
                buttons[i].setFont(new Font("Verdana", Font.BOLD, fontSize));
                buttons[i].setBackground(Color.black);
                buttons[i].setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
                // BorderFactory.createEmptyBorder()
                buttons[i].setActionCommand(commands[i]);
                buttons[i].addActionListener(this);
                buttonPanels[i].add(buttons[i]);
                if (i != 2 || importantTask) {
                    buttonPane.add(buttonPanels[i]);
                }
            }
            getRootPane().setDefaultButton(buttons[3]);
            getContentPane().add(buttonPane, BorderLayout.CENTER);

            //Initialize values.
            pack();
            setLocationRelativeTo(frame);
        }

        public String showDialogue() {
            setVisible(true);
            return clickedOn;
        }

        public void actionPerformed(ActionEvent e) {
            if ("done".equals(e.getActionCommand())) {
                clickedOn = "done";
                setVisible(false);
            } else if ("change".equals(e.getActionCommand())) {
                clickedOn = "change";
                setVisible(false);
            } else if ("promote".equals(e.getActionCommand())) {
                clickedOn = "promote";
                setVisible(false);
            } else if ("cancel".equals(e.getActionCommand())) {
                clickedOn = "cancel";
                setVisible(false);
            }
        }

        public BufferedImage getButtonImage(String[] labels, int index) {

            BufferedImage temp = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) temp.createGraphics();
            Font f = new Font("Verdana", Font.BOLD, 14);
            int w = 100;
            GlyphVector gl;
            Rectangle2D labelBox;
            for (int i = 0; i < labels.length; i++) {
                gl = f.createGlyphVector(g.getFontRenderContext(), labels[i]);
                labelBox = gl.getVisualBounds();
                if (labelBox.getWidth() > w) {
                    w = (int) labelBox.getWidth();
                }
            }
            int offsetX = 20;
            int width = w + 2 * offsetX;

            // now we know the width, set up the image
            int height = 32;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = (Graphics2D) bi.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // set the background
            g.setPaint(Color.black);
            Rectangle2D.Double bg = new Rectangle2D.Double(0.0, 0.0, (double) width, (double) height);
            g.fill(bg);

            // now for a grey gradient fill in a roudned rectangle shape
            RoundRectangle2D.Float edge = new RoundRectangle2D.Float(1.0f, 1.0f, width - 3.0f, height - 3.0f, 4.0f, 4.0f);
            Color bgc = new Color(220, 220, 220);
            g.setPaint(new GradientPaint(20.0f, 0.0f, bgc,
                    20.0f, (float) height, bgc.darker().darker()));
            g.fill(edge);

            // now to define the edge
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.setPaint(new Color(120, 120, 120));
            g.draw(edge);

            // and now for the label
            g.setFont(f);
            g.setPaint(Color.black);
            gl = f.createGlyphVector(g.getFontRenderContext(), labels[index]);
            Rectangle2D box = gl.getVisualBounds();
            int y = (height + fontSize) / 2;
            g.drawString(labels[index], (int) ((width - box.getWidth()) / 2.0), y);

            return bi;
        }
    } // end of class TaskClickDialogue
}
