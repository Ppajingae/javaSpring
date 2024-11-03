package com.yoong.javaspring2.order.service;

import com.yoong.javaspring2.order.entity.Order;

public interface OrderService {

    Order createOrder(Long id, String itemName, int itemPrice);
}
