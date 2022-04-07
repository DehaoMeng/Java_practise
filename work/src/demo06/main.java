package demo06;


public class main {
    public static void main(String[] args) {
        test_Hobbit();
        test_Wizard();
        test_World();
    }
    public static void test_World(){
        World wd = new World("middle_ages");
        Wizard x = new Wizard("younger",100,30,90,101);
        wd.addEntity(x);
        System.out.println(wd.getEntity(1));
    }

    public static void test_Wizard(){
        Wizard x = new Wizard("younger",100,30,90,101);
        System.out.println(x.toString());
        x.move();
        System.out.println(x.toString());
        Wizard y = new Wizard("younger",100,30,101,80);
        System.out.println(y.toString());
        y.move();
        System.out.println(y.toString());

        Wizard z = new Wizard("younger",100,30,90);
        System.out.println(z.toString());
        z.move();
        System.out.println(z.toString());

        Wizard e = new Wizard("younger",100,30,101);
        System.out.println(e.toString());
        e.move();
        System.out.println(e.toString());
    }

    public static void test_Hobbit(){
        Hobbit x = new Hobbit("older",50,10,100);
        System.out.println(x.toString());
        x.move();
        System.out.println(x.toString());
        Hobbit y = new Hobbit("younger",100,80);
        System.out.println(y.toString());
        y.move();
        System.out.println(y.toString());
    }
}
