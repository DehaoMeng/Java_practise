package demo01;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class world {
    private entity[] middle_ages;
    // 构造函数分别创建四个实体
    world(){
        middle_ages = new entity[4];
        ArrayList<String> list = new ArrayList<>();
        list.add("Frodo");
        list.add("Gandalf");
        list.add("Bilbo");
        list.add("Fili");
        for (int i = 0; i < 4; i++) {
            String s = list.get(i);
            middle_ages[i] = new entity();
            middle_ages[i].setName(s);
        }
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 4; i++)
        {
            String s = list.get(i);
            System.out.println("Enter x of " + (i+1) + " the entity " + middle_ages[i].getName());
            int x = input.nextInt();
            System.out.println("Enter x of " + (i+1) + " the entity " + middle_ages[i].getName());
            int y = input.nextInt();
            set(i,x,y);
        }
        show_all();
    }
    // 输入一个实体名称 随机更改其位置
    public void set_world_input_name_random()
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
                show_all();
                System.out.println(middle_ages[i].getName() + " found, moved and deleted.");
                int n = 3;
                show_all(s,n);
            }
            else if (Objects.equals(i, 3) && !Objects.equals(middle_ages[i].getName(), s))
            {
                System.out.println(s);
                show_all();
            }
        }
    }
    // 设置实体位置
    private void set(int i, int x, int y) {
        middle_ages[i].setX(x);
        middle_ages[i].setY(y);
    }
    // 展示所有实体
    private void show_all()
    {
        System.out.println("World name: Middle Ages");
        System.out.println("The number of entities: 4");
        System.out.println("Each entity:");
        for (entity x: middle_ages)
        {
            System.out.println("Name: " + x.getName() + ", The position is (" + x.getX() + "," + x.getY() + ")");
        }
    }
    // 展示除被删掉外的实体。
    private void show_all(String s,int n)
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
