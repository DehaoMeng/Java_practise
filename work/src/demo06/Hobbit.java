package demo06;

import java.util.Random;

// 继承Entity类创建子类
public class Hobbit extends Entity{
    int health;

    public Hobbit(String name, int x, int y, int health) {
        super(name, "#", x, y);
        this.health = health;
    }

    public Hobbit(String name, int x, int y) {
        super(name, "#", x, y);
        this.health = 200;
    }

    // 重写父类Entity中的移动方法
    @Override
    public void move(){
        health = health - 1;
        Random r = new Random();
        x = r.nextInt(6) + x - 3;
        y = r.nextInt(6) + x - 3;
    }

    @Override
    public String toString() {
        return "Hobbit{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", health=" + health +
                '}';
    }
}
