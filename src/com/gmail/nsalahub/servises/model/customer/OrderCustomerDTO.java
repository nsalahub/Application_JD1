package com.gmail.nsalahub.servises.model.customer;

import com.gmail.nsalahub.reposetory.model.OrderStatus;

import java.util.Date;

public class OrderCustomerDTO {

    private Integer quantity;
    private OrderStatus orderStatus;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
