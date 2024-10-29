package com.yoong.javaspring2.java_study.object.tostring;

public class ToStringMain2 {

    public static void main(String[] args) {

        Car car = new Car("페라리");
        Dog dog1 = new Dog("개1",10);
        Dog dog2 = new Dog("개2",1);

        System.out.println("car : " + car.toString());
        System.out.println("dog1 : " + dog1.toString());
        System.out.println("dog2 : " + dog2.toString());

        System.out.println("car : " + car);
        System.out.println("dog1 : " + dog1);
        System.out.println("dog2 : " + dog2);

        ObjectPrinter.print(car);
        ObjectPrinter.print(dog1);
        ObjectPrinter.print(dog2);
    }
}
