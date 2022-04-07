package demo07;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static World MW = new World("My World");
    public static void main(String[] args){
        while (true) {
            System.out.println("1 Ask user for the name of a Hobbit and add it to the World with default health 100");
            System.out.println("2 Ask user for the name and age of a Wizard and add it to the World with wisdom 50");
            System.out.println("3 Display information about the name of the World and all entities stored in the World");
            System.out.println("4 Move all entities in the world about times");
            System.out.println("0 Stop the program");
            System.out.println("Please choose one option:");
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();
            switch (i) {
                case 4 -> Move();
                case 3 -> Display();
                case 2 -> AddWizard();
                case 1 -> AddHobbit();
                case 0 -> System.exit(0);
                default -> {
                    System.out.println("InputError please input again!");
                }
            }
            System.out.println("What do you want to do next?");
        }
    }
    public static void AddHobbit(){
        Scanner input = new Scanner(System.in);
        System.out.println("What's the name of the Hobbit?");
        String name = String.valueOf(input.next());
        Hobbit ht = new Hobbit(name,100);
        MW.addEntity(ht);
    }
    public static void AddWizard(){
        Scanner input = new Scanner(System.in);
        System.out.println("What's the name and age of the Wizard?");
        String name = String.valueOf(input.next());
        int age = input.nextInt();
        Wizard wd = new Wizard(name,age,50);
        MW.addEntity(wd);
    }
    public static void Display(){
        System.out.println("The World's name is:" + MW.getName());
        System.out.println("All of the Hobbits abd Wizards:");
        ArrayList<Entity> team = MW.getTeam();
        for (Entity e:team){
            if ("#".equals(e.getSymbol())){
                System.out.println("Hobbit:" + e.toString());
            }
            else if ("@".equals(e.getSymbol())){
                System.out.println("Wizard:" + e.toString());
            }
        }
    }
    public static void Move(){
        ArrayList<Entity> team = MW.getTeam();
        for (Entity e:team){
            e.move();
        }
    }
}
