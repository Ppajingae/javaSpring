package com.yoong.javaspring2.java_study.object;

public class ChildMain {

    public static void main(String[] args) {

        Child child = new Child();

        child.childMethod();
        child.parentMethod();

        String str = child.toString();

        System.out.println(str);
    }
}
