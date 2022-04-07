package demo02;

import java.util.Scanner;

public class entitydemo {
    public static void main(String[] args) {
        // 新建世界
        world wd = create_world();
        // 依次设置实体位置
        set_Entity(wd);
        // 输入实体名称并更改位置，删除并输出世界全部内容，如果输入的实体不存在则输出世界全部内容
        setup_eneity(wd);
    }
    public static void set_Entity(world wd){
        // 设置实体位置
        wd.set_Entity();
    }
    public static void setup_eneity(world wd){
        // 输入一个实体名称更改其位置并删除模块
        Scanner input = new Scanner(System.in);
        System.out.println("Enter one entity name and move this entity to one random position:");
        String s = String.valueOf(input.next());
        // 随机移动更爱实体位置。并删除。
        wd.moveEntity(s);
    }
    public static world create_world() {
        // 新建世界并在世界中创建实体，返回世界。
        world wd = new world();
        wd.setName("Middle Ages");
        wd.add_Entity("Frodo");
        wd.add_Entity("Gandalf");
        wd.add_Entity("Bilbo");
        wd.add_Entity("Fili");
        return wd;
    }
}