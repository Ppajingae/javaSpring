package com.yoong.javaspring2.advanced.order.service;

import com.yoong.javaspring2.advanced.order.repository.OrderRepositoryV1;
import com.yoong.javaspring2.advanced.order.repository.OrderRepositoryV2;
import com.yoong.javaspring2.advanced.trace.TraceId;
import com.yoong.javaspring2.advanced.trace.TraceStatus;
import com.yoong.javaspring2.advanced.trace.log.TraceLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final TraceLogger logger;

    public void orderItem(TraceId traceId, String itemId) {

        TraceStatus status = null;

        try {

            status = logger.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            logger.end(status);

        }catch (Exception e){

            logger.exception(status, e);
            throw e;
        }


    }


}
