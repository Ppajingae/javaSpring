package com.yoong.javaspring2.advanced.order.controller;


import com.yoong.javaspring2.advanced.order.service.OrderServiceV2;
import com.yoong.javaspring2.advanced.order.service.OrderServiceV3;
import com.yoong.javaspring2.advanced.trace.TraceStatus;
import com.yoong.javaspring2.advanced.trace.log.TraceLogger;
import com.yoong.javaspring2.advanced.trace.log_trace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace logger;

    @GetMapping("/v3/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try {

            status = logger.begin("OrderController.request()");
            orderService.orderItem(itemId);
            logger.end(status);
            return "success";

        }catch (Exception e){

            logger.exception(status, e);
            throw e;
        }
    }
}
