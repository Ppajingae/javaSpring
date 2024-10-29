package com.yoong.javaspring2.java_study.object.poly;

public class ObjectPoleExample1 {

    public static void main(String[] args) {

        Car car = new Car();
        Dog dog = new Dog();

        action(car);
        action(dog);
    }

    public static void action(Object obj){
        if(obj instanceof Dog dog){
            dog.sound();
        }else if(obj instanceof Car car){
            car.move();
        }
    }
}
