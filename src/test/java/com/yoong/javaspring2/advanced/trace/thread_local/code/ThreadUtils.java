package com.yoong.javaspring2.advanced.trace.thread_local.code;

public class ThreadUtils {

    public static void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
