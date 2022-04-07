package demo06;

import java.util.Random;

public class Wizard extends Entity{
    int age;
    int wisdom;

    public Wizard(String name, int x, int y,int age,int wisdom) {
        super(name, "@", x, y);
        this.age = age;
        this.wisdom = wisdom;
    }

    public Wizard(String name, int x, int y, int age) {
        super(name, "@", x, y);
        this.age = age;
        this.wisdom = 100;
    }

    @Override
    public void move(){
        if (age < 100){
            Random r = new Random();
            x = r.nextInt(100);
            y = r.nextInt(100);
        }
        else {
            Random r = new Random();
            x = r.nextInt(10) + x - 5;
            y = r.nextInt(10) + x - 5;
        }
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", age=" + age +
                ", wisdom=" + wisdom +
                '}';
    }
}
