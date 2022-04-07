package demo06;

public class World {
    private String name; // 世界名称
    int MaxSize = 10; // 最大存储实体的数量
    int Size = 0; // 当前实体数量
    Entity[] et = new Entity[MaxSize];

    public World(String name) {
        this.name = name;
    }
    public void addEntity(Entity entity){
        if (Size < MaxSize) {
            et[Size] = entity;
            Size += 1;
        }
        else {
            System.out.println("已满");
        }
    }
    public void deleteEntity(int pos) {
        if (pos < 1 || pos > Size){
            System.out.println("输入位置有误");
            return;
        }
        for (int i = pos-1; i < Size-1; i++) {
            et[i] = et[i+1];
        }
        Size -= 1;
    }
    public Entity getEntity(int pos){
        return et[pos-1];
    }
}
