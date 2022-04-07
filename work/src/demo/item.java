package demo;

public class item {
    private String name;
    private double price;
    private String origin;

    public item(String name, double price) {
        this.name = name;
        this.price = price;
        this.origin = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getName() {
        return name;
    }

    public void MessageAll() {
        System.out.println("产品名称: " + this.name + "； 产品价格: " + this.price + "； 产品产地: " + this.origin + ";");
    }
}
