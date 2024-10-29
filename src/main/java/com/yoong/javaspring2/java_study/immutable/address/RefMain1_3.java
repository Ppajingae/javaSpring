package com.yoong.javaspring2.java_study.immutable.address;

public class RefMain1_3 {

    public static void main(String[] args) {

        Address seoul = new Address("Seoul");
        Address su = seoul;

        System.out.println("seoul = " + seoul);
        System.out.println("su = " + su);

        change(su, "tokyo");

        System.out.println("------------------------");
        System.out.println("seoul = " + seoul);
        System.out.println("su = " + su);
    }

    private static void change(Address address, String changeAddress) {
        System.out.println("주소 값을 변경 합니다 -> " +  changeAddress);
        address.setValue(changeAddress);

    }
}
