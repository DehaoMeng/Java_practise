package demo01;

import java.util.*;

public class entitydemo {
    public static void main(String[] args) {
        // 设置实体名称 将名称存入到列表中
        ArrayList<String> list = new ArrayList<>();
        list.add("Frodo");
        list.add("Gandalf");
        list.add("Bilbo");
        list.add("Fili");
        entity[] middle_ages = new entity[4];
        for (int i = 0; i < 4; i++) {
            String s = list.get(i);
            middle_ages[i] = new entity();
            middle_ages[i].setName(s);
        }
        set_world_input(middle_ages);
        set_world_input_name_random(middle_ages);
    }

    // 输入一个实体名称 如果该实体存在 则随机更改该实体的位置，世界中删除实体，打印出实体"找到、移动和删除"然后再次显示所有实体。如果不存在请 打印出实体"不退出！"并显示所有实体。
    public static <n> void set_world_input_name_random(entity[] middle_ages)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter one entity name and move this entity to one random position: ");
        String s = String.valueOf(input.next());
        for (int i = 0; i < 4; i++) {
            if (Objects.equals(middle_ages[i].getName(), s))
            {
                Random r = new Random();
                middle_ages[i].setX(r.nextInt(101));
                middle_ages[i].setY(r.nextInt(101));
                show_all(middle_ages);
                System.out.println(middle_ages[i].getName() + " found, moved and deleted.");
                int n = 3;
                show_all(middle_ages,s,n);
            }
            else if (Objects.equals(i, 3) && !Objects.equals(middle_ages[i].getName(), s))
            {
                System.out.println(s);
                show_all(middle_ages);
            }
        }

    }
    // 输入设置实体位置信息
    public static void set_world_input(entity[] middle_ages)
    {

        Scanner input = new Scanner(System.in);
        int y = 1;
        for (entity x:middle_ages) {
            System.out.println("Enter x of " + y + " the entity" + x.getName());
            int p = input.nextInt();
            x.setX(p);
            System.out.println("Enter x of " + y + " the entity" + x.getName());
            p = input.nextInt();
            x.setY(p);
            y = y + 1;
        }
        show_all(middle_ages);
    }
    public static void show_all(entity[] middle_ages)
    {
        System.out.println("World name: Middle Ages");
        System.out.println("The number of entities: 4");
        System.out.println("Each entity:");
        for (entity x: middle_ages)
        {
            System.out.println("Name: " + x.getName() + ", The position is (" + x.getX() + "," + x.getY() + ")");
        }
    }
    public static void show_all(entity[] middle_ages,String s,int n)
    {
        System.out.println("World name: Middle Ages");
        System.out.println("The number of entities: " + n);
        System.out.println("Each entity:");
        for (entity x: middle_ages)
        {
            if(!Objects.equals(s, x.getName()))
            {
                System.out.println("Name: " + x.getName() + ", The position is (" + x.getX() + "," + x.getY() + ")");
            }
        }
    }
}
