package demo06;

public abstract class Entity {
    String name;
    String symbol;
    int x,y;
    public abstract void move();
    World wd;

    public World getWd() {
        return wd;
    }

    public void setWd(World wd) {
        this.wd = wd;
    }

    public Entity(){}

    public Entity(String name, String symbol, int x, int y) {
        this.name = name;
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
