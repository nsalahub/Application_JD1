package com.gmail.nsalahub.reposetory.model.customer;

import com.gmail.nsalahub.reposetory.model.OrderStatus;

import java.util.Date;

public class OrderCustomer {
    private Date createDate;
    private Integer quantity;
    private OrderStatus orderStatus;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

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
