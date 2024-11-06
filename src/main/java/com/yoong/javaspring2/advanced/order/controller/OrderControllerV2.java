package com.yoong.javaspring2.advanced.order.controller;


import com.yoong.javaspring2.advanced.order.service.OrderServiceV1;
import com.yoong.javaspring2.advanced.order.service.OrderServiceV2;
import com.yoong.javaspring2.advanced.trace.TraceStatus;
import com.yoong.javaspring2.advanced.trace.log.TraceLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final TraceLogger logger;

    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try {

            status = logger.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            logger.end(status);
            return "success";

        }catch (Exception e){

            logger.exception(status, e);
            throw e;
        }
    }
}
