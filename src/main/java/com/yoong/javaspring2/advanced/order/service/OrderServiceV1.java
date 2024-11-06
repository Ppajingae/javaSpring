package com.yoong.javaspring2.advanced.order.service;

import com.yoong.javaspring2.advanced.order.repository.OrderRepositoryV0;
import com.yoong.javaspring2.advanced.order.repository.OrderRepositoryV1;
import com.yoong.javaspring2.advanced.trace.TraceStatus;
import com.yoong.javaspring2.advanced.trace.log.TraceLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final TraceLogger logger;

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
