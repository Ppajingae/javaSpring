package com.yoong.javaspring2.advanced.order.repository;

import com.yoong.javaspring2.advanced.trace.TraceId;
import com.yoong.javaspring2.advanced.trace.TraceStatus;
import com.yoong.javaspring2.advanced.trace.log.TraceLogger;
import com.yoong.javaspring2.advanced.trace.log_trace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace logger;

    public void save(String itemId) {

        TraceStatus status = null;

        try {

            status = logger.begin("OrderRepository.save()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            logger.end(status);

        } catch (Exception e) {

            logger.exception(status, e);
            throw e;
        }


    }

    private void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
