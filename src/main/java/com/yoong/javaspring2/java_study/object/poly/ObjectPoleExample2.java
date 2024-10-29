package com.yoong.javaspring2.java_study.object.poly;

public class ObjectPoleExample2 {

    public static void main(String[] args) {

        Dog dog = new Dog();
        Car car = new Car();
        Object obj = new Object();
        Object[] objArr = {dog, car, obj};

        size(objArr);
    }

    private static void size(Object[] objArr) {

        System.out.println(objArr.length);
    }
}
