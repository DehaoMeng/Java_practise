package demo;/* U08223 Week 1
 *
 * This class provides methods to manage the two lists of tasks.
 * Your code will complete the methods below.
 */

import java.util.*;

public class ListsManager {

    public ArrayList<String> highPriority, lowPriority;  // the two lists of tasks
	
    public TaskListGUI controller; // a reference to the main program, with the GUI

    /* Constructs a ListManager object. */
    public ListsManager(TaskListGUI ref) {
        // (you should not change the code in this method)
        controller = ref;		

    /* Initialises both lists, to initially be empty. */
        /* 新建列表置为空 */
        Constructor();
    }

    /* Adds the given task to the end of the low-priority list.
     */
    public void addTask(String task) {
        /* 增加任务 */
        String important = task.replace("#", "");
        if (task == important)
        {
            lowPriority.add(task);
        }
        else {
            highPriority.add(important);
        }
    }

    /* Removes the given task to the end of the high-priority list.
     * The index integer gives the current position of the task in
     * the relevant list of tasks. (You can assume that index is a
     * valid position in the relevant list.)
     */
    /* 删除低优先级任务*/
    public void removeLowPriorityTask(int index) {
        lowPriority.remove(index);
    }


    /* Removes the given task from the high-priority list.
     * The index integer gives the current position of the task in
     * the relevant list of tasks. (You can assume that index is a
     * valid position in the relevant list.)
     */
    /* 删除高优先级任务*/
    public void removeHighPriorityTask(int index) {
        highPriority.remove(index);
    }

    /* Changes the priority of the given task.
     * The given boolean important indicates the previous priority
     * of the task. e.g. if important is true, then the task was
     * a high-priority task.
     * The index integer gives the current position of the task in
     * the relevant list of tasks. (You can assume that index is a
     * valid position in the relevant list.)
     */
    /* 修改任务优先级 */
    public void changePriority(boolean important, int index) {
            if (important){
                String task = highPriority.get(index);
                highPriority.remove(index);
                lowPriority.add(0,task);
            }
            else {
                String task = lowPriority.get(index);
                lowPriority.remove(index);
                highPriority.add(task);
            }

    }

    /* Produces a string that can be used to save the task lists. */
    public String toString() {
        return "";  // dummy return value (before this method is implemented)
    }

    /* Pushes an important task one higher up.
     * The index gives the current position of the task in the
     * highPriority list. (You can assume that index is a
     * valid position in the highPriority list.)
     */
    /* 提高高优先级任务中的执行顺序 */
    public void promote(int index) {
        String task = highPriority.get(index);
        highPriority.remove(index);
        highPriority.add(0,task);
    }

    void initialiseLists() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* 新建任务列表 */
    public void Constructor(){
        highPriority = new ArrayList<>();
        lowPriority = new ArrayList<>();
    }
}
