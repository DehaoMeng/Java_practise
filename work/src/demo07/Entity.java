package demo07;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public abstract class Entity {

    private String name;
    private String symbol;
    private int x, y;
    private World world;

    public Entity(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public abstract void move();
    // getters an setters

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return name + " " + symbol + " position " + x + " " + y;
    }
}
