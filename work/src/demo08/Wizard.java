package demo08;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;

public class Wizard extends Entity {

    private int age;
    private int wisdom;

    public Wizard(String name, int age, int wisdom) {
        super(name, "@");
        this.age = age;
        this.wisdom = wisdom;
    }

    public Wizard(String name, int age) {
        super(name, "@");
        this.age = age;
        this.wisdom = 100;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    private int jump(int backOrForward, int step) {
        if (backOrForward == 0) {
            return -step;
        } else {
            return step;
        }
    }

    public void move() {
        Random random = new Random();

        if (age < 100) {
            setX(random.nextInt(101));
            setY(random.nextInt(101));
        } else {
            int step = random.nextInt(6);// jump 0 to 5 
            int backOrForward = random.nextInt(2); // 0 - back , 1 - forward
            setX(jump(backOrForward, step));

            step = random.nextInt(6);// jump 0 to 5 
            backOrForward = random.nextInt(2); // 0 - back , 1 - forward
            setY(jump(backOrForward, step));
        }
    }

    @Override
    public String toString() {
        return super.toString() + " wisdom " + wisdom + " age " + age;
    }
}
