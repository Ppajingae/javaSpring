package com.yoong.javaspring2.advanced.order.service;

import com.yoong.javaspring2.advanced.order.repository.OrderRepositoryV2;
import com.yoong.javaspring2.advanced.order.repository.OrderRepositoryV3;
import com.yoong.javaspring2.advanced.trace.TraceId;
import com.yoong.javaspring2.advanced.trace.TraceStatus;
import com.yoong.javaspring2.advanced.trace.log.TraceLogger;
import com.yoong.javaspring2.advanced.trace.log_trace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace logger;

    public void orderItem(String itemId) {

        TraceStatus status = null;

        try {

            status = logger.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            logger.end(status);

        }catch (Exception e){

            logger.exception(status, e);
            throw e;
        }


    }


}
