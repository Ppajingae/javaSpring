package com.yoong.javaspring2.advanced.trace.thread_local;

import com.yoong.javaspring2.advanced.trace.thread_local.code.FieldService;
import com.yoong.javaspring2.advanced.trace.thread_local.code.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        ThreadUtils.sleep(2000);
        ThreadUtils.sleep(100); // 동시성 문제 발생 O
        threadB.start();

        ThreadUtils.sleep(3000);
        log.info("main exit");
    }

}
