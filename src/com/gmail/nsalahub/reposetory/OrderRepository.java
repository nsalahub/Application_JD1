package com.gmail.nsalahub.reposetory;

import com.gmail.nsalahub.reposetory.model.Order;
import com.gmail.nsalahub.reposetory.model.OrderStatus;
import com.gmail.nsalahub.reposetory.model.customer.OrderCustomer;
import com.gmail.nsalahub.reposetory.model.sale.OrderSale;
import com.gmail.nsalahub.servises.exeptions.LoginException;
import com.gmail.nsalahub.servises.exeptions.OrderException;

import java.sql.Connection;
import java.util.List;

public interface OrderRepository {

    void insertOrder(Connection connection, OrderCustomer order, long userId, String itemName) throws LoginException, OrderException;

    void updateStatusByUserId(Connection connection, OrderStatus orderStatus, long userId) throws LoginException, OrderException;

    void updateStatusByItemId(Connection connection, OrderStatus orderStatus, long itemId) throws LoginException, OrderException;

    List<OrderSale> getOrders(Connection connection, Integer page, Integer orderLimit) throws LoginException, OrderException;


    Integer getValueOrders(Connection connection) throws LoginException, OrderException;
}
