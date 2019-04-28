package com.gmail.nsalahub.reposetory.model.sale;

import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.reposetory.model.OrderStatus;
import com.gmail.nsalahub.reposetory.model.User;

import java.util.Date;

public class OrderSale {
    private long id;
    private UserSale user;
    private ItemSale item;
    private Date createDate;
    private Integer quantity;
    private OrderStatus orderStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserSale getUser() {
        return user;
    }

    public void setUser(UserSale user) {
        this.user = user;
    }

    public ItemSale getItem() {
        return item;
    }

    public void setItem(ItemSale item) {
        this.item = item;
    }

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
