package com.yoong.javaspring2.singleton;

public class StateFulService {

    private int price;

    public void order(String name, int price){
        System.out.println("name + \", price = \" + price = " + name + ", price = " + price);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
