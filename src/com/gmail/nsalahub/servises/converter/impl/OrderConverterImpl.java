package com.gmail.nsalahub.servises.converter.impl;


import com.gmail.nsalahub.reposetory.model.Order;
import com.gmail.nsalahub.servises.converter.OrderConverter;
import com.gmail.nsalahub.servises.converter.UserConverter;
import com.gmail.nsalahub.servises.model.OrderDTO;

public class OrderConverterImpl implements OrderConverter {

    private static OrderConverterImpl instance;

    private OrderConverterImpl() {
    }

    public static OrderConverterImpl getInstance() {
        if (instance == null) {
            instance = new OrderConverterImpl();
        }
        return instance;
    }

    private UserConverter userConverter = UserConverterImpl.getInstance();

    @Override
    public Order fromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(userConverter.fromDTO(orderDTO.getUser()));
        order.setQuantity(orderDTO.getQuantity());
        order.setCreateDate(orderDTO.getCreateDate());
        order.setOrderStatus(orderDTO.getOrderStatus());
        return order;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser(userConverter.toDTO(order.getUser()));
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setCreateDate(order.getCreateDate());
        orderDTO.setQuantity(order.getQuantity());
        return orderDTO;
    }
}
