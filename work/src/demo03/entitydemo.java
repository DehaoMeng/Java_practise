package demo03;

import java.util.Scanner;

public class entitydemo {
    public static void main(String[] args) {
        // 新建世界并在世界中创建实体
        world md = new world();
        create_world(md);
        // 依次设置实体位置
        md.setEntity();
        md.display();
        setup_eneity(md);
    }
    public static void setup_eneity(world md){
        // 输入一个实体名称更改其位置并删除模块
        Scanner input = new Scanner(System.in);
        System.out.println("Enter one entity name and move this entity to one random position:");
        String s = String.valueOf(input.next());
        // 随机移动更爱实体位置。并删除。
        md.moveEntity(s);
    }
    public static void create_world(world md) {
        md.setName("Middle Ages");
        md.addEntity("Frodo");
        md.addEntity("Gandalf");
        md.addEntity("Bilbo");
        md.addEntity("Fili");
    }
}