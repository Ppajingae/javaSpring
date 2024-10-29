package com.yoong.javaspring2.java_study.immutable.address;

public class RefMain1_1 {

    public static void main(String[] args) {

        Address seoul = new Address("Seoul");
        Address su = seoul;

        System.out.println("seoul = " + seoul);
        System.out.println("su = " + su);

        su.setValue("Tokyo");

        System.out.println("------------------------");
        System.out.println("seoul = " + seoul);
        System.out.println("su = " + su);
    }
}
