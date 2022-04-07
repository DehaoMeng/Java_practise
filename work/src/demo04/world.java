package demo04;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class world {
    private String name; // 世界名称
    int MaxSize = 10; // 最大存储实体的数量
    int Size = 0; // 当前实体数量
    entity[] et = new entity[MaxSize];
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addEntity(String name){
        if (Size >= MaxSize){
            System.out.println("世界已达最大容量");
            return;
        }
        et[Size] = new entity();
        et[Size].setName(name);
        Size = Size + 1;
    }
    public void deleteEntity(int x,int y){
        for (int i = 0; i < Size; i++) {
            if (et[i].getX() == x && et[i].getY() == y) {
                for(int j = i; j < Size; j++){
                    et[j] = et[j+1];
                }
                Size = Size - 1;
                return;
            }
        }
        System.out.println("该位置实体不存在");
    }
    public int[] findEntity(String name){
        for (int i = 0; i < Size; i++) {
            if (Objects.equals(et[i].getName(), name))
            {
                int[] location = new int[2];
                location[0] = et[i].getX();
                location[1] = et[i].getY();
                return location;
                }
            }
        return new int[1];
    }
    public void moveEntity(String name) {
        for (int i = 0; i <Size ; i++) {
            if (Objects.equals(et[i].getName(), name)) {
                Random r = new Random();
                et[i].setX(r.nextInt(101));
                et[i].setY(r.nextInt(101));
                display();
                System.out.println(et[i].getName() + " found, moved and deleted.");
                int[] fl = findEntity(name);
                if (!Objects.equals(fl[0],-1))
                {
                    deleteEntity(fl[0],fl[1]);
                }
            }
        }
        display();
    }
    public void display(){
        System.out.println("World name: " + name);
        System.out.println("The number of entities: " + Size);
        System.out.println("Each entity:");
        for (int i = 0; i < Size; i++) {
            System.out.println("Name: " + et[i].getName() + ", The position is (" + et[i].getX() + "," + et[i].getY() + ")");
        }
    }
    public void setEntity(){
        int i = 1;
        for (int j = 0; j < Size; j++) {
            System.out.println("Enter x of " + i + " the entity " + et[j].getName());
            Scanner input = new Scanner(System.in);
            int s = input.nextInt();
            et[j].setX(s);
            System.out.println("Enter y of " + i + " the entity " + et[j].getName());
            s = input.nextInt();
            et[j].setY(s);
            i = i + 1;
        }
    }
}
