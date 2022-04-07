package demo08; // 这里需要自己更改一致

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static World world = new World("My World");
    public static void main(String[] args){
        // 开始入口
        start();
    }
    public static void start(){
        // 无限循环操作  直到输入0退出
        while(true)
            // 打印选择内容
            Print();
    }
    public static void Choose(){
        Scanner input = new Scanner(System.in);
        switch (input.nextInt()) {
            case 0 -> System.exit(0); // 退出
            case 1 -> AddHobbit(); // 添加Hobbit实体
            case 2 -> AddWizard(); // 添加Wizard实体
            case 3 -> Show(); // 展示世界中的实体内容
            case 4 -> Move(); // 输入为4 对每一项进行移动
        }
    }
    // 打印选择内容
    public static void Print(){
        System.out.println("1 Ask user for the name of a Hobbit and add it to the World with default health 100");
        System.out.println("2 Ask user for the name and age of a Wizard and add it to the World with wisdom 50");
        System.out.println("3 Display information about the name of the World and all entities stored in the World");
        System.out.println("4 Move all entities in the world about times");
        System.out.println("0 Stop the program");
        System.out.println("Please choose one option:");
        Choose(); // 输入选择 进行判断
        System.out.println("What do you want to do next?");//进入下一次循环
    }
    // 添加Hobbit实体
    public static void AddHobbit(){
        Scanner input = new Scanner(System.in);
        System.out.println("What's the name of the Hobbit?");
        Hobbit h = new Hobbit(String.valueOf(input.next()),100);
        world.addEntity(h);
    }
    // 添加Wizard实体
    public static void AddWizard(){
        Scanner input = new Scanner(System.in);
        System.out.println("What's the name and age of the Wizard?");
        Wizard w = new Wizard(String.valueOf(input.next()),input.nextInt(),50);
        world.addEntity(w);
    }
    // 展示实体中的内容
    public static void Show(){
        System.out.println("The World's name is:" + world.getName());
        System.out.println("All of the Hobbits abd Wizards:");
        ArrayList<Entity> team = world.getTeam();
        for (Entity x:team){
            if ("#".equals(x.getSymbol())){
                System.out.println("Hobbit:" + x.toString());
            }
            else{
                System.out.println("Wizard:" + x.toString());
            }
        }
    }
    // 移动世界中的每一项实体
    public static void Move(){
        ArrayList<Entity> team = world.getTeam();
        for (Entity x:team){
            x.move();
        }
    }
}
