package demo07;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;

public class Hobbit extends Entity {

    private int health;

    public Hobbit(String name, int health) {
        super(name, "#");
        this.health = health;
    }

    public Hobbit(String name) {
        super(name, "#");
        this.health = 200;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void move() {
        Random random = new Random();
        int rx = random.nextInt(3);// generate 0, 1 or 2 stand three situation
        int ry = random.nextInt(3);// generate 0, 1 or 2 stand three situation
        //0 means no move, 1 menas move 1, 2 means move -1. 
        switch (rx) {
            case 1:
                setX(getX() + 1);
                break;
            case 2:
                setX(getX() - 1);
                break;
        }
        switch (ry) {
            case 1:
                setY(getY() + 1);
                break;
            case 2:
                setY(getX() - 1);
                break;
        }
        health--; //every time that they move his health is decreased by one
    }

    @Override
    public String toString() {
        return super.toString() + " health " + health;
    }
}
