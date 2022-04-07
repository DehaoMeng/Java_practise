package demo;

import java.util.Scanner;

public class itemdemo {
    static item[] bill = new item[3];
    public static void main(String[] args) {
        // 创建一个bill对象
//        item[] bill = new item[3];
        // 输入并设置各项内容
        for (int i = 0; i < 3; i++) {
            Setbill(i);
        }
        Printbill();
    }
    public static void Setbill(int i) {
        // 从键盘接收数据
        Scanner input=new Scanner(System.in);
        System.out.println("Item " + (i+1)+ " Name");
       // 输入产品名称
        String x = String.valueOf(input.next());
        // 输入产品价格
        System.out.println("Item " + (i+1)+ " Price");
        double y = input.nextDouble();
        // 账单中创建该产品
        bill[i] = new item(x,y);
        System.out.println("Item " + (i+1)+ " origin");
        // 输入产品产地
        x = String.valueOf(input.next());
        // 账单中设置产地
        bill[i].setOrigin(x);
    }
    public static void Printbill()
    {
        // 打印每个对象
        for (int i = 0; i < 3; i++) {
            System.out.println("Item " + (i+1));
            bill[i].MessageAll();
        }
        // 获取账单总价格并打印出来
        System.out.println("Total Payment: " + (bill[0].getPrice()+bill[1].getPrice()+bill[2].getPrice()));
    }
}
