package com.yoong.javaspring2.java_study.immutable.address;

public class RefMain1_2 {

    public static void main(String[] args) {

        Address seoul = new Address("Seoul");
        Address tokyo = new Address("Seoul");

        System.out.println("seoul = " + seoul);
        System.out.println("tokyo = " + tokyo);

        tokyo.setValue("Tokyo");

        System.out.println("------------------------");
        System.out.println("seoul = " + seoul);
        System.out.println("tokyo = " + tokyo);
    }
}
