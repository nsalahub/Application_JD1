package com.gmail.nsalahub.servises.model.sale;

import com.gmail.nsalahub.reposetory.model.OrderStatus;

import java.util.Date;

public class OrderSaleDTO {
    private long id;
    private UserSaleDTO user;
    private ItemSaleDTO item;
    private Date createDate;
    private Integer quantity;
    private OrderStatus orderStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserSaleDTO getUser() {
        return user;
    }

    public void setUser(UserSaleDTO user) {
        this.user = user;
    }

    public ItemSaleDTO getItem() {
        return item;
    }

    public void setItem(ItemSaleDTO item) {
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
