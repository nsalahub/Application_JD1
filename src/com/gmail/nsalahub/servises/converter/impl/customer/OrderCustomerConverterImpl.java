package com.gmail.nsalahub.servises.converter.impl.customer;

import com.gmail.nsalahub.reposetory.model.customer.OrderCustomer;
import com.gmail.nsalahub.servises.converter.OrderCustomerConverter;
import com.gmail.nsalahub.servises.model.customer.OrderCustomerDTO;

public class OrderCustomerConverterImpl implements OrderCustomerConverter {

    private static OrderCustomerConverterImpl instance;

    private OrderCustomerConverterImpl (){}

    public static OrderCustomerConverterImpl getInstance(){
        if (instance == null){
            instance = new OrderCustomerConverterImpl();
        }
        return instance;
    }

    @Override
    public OrderCustomer fromDTO(OrderCustomerDTO orderCustomerDTO) {
        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setOrderStatus(orderCustomerDTO.getOrderStatus());
        orderCustomer.setQuantity(orderCustomerDTO.getQuantity());
        return orderCustomer;
    }

    @Override
    public OrderCustomerDTO toDTO(OrderCustomer orderCustomer) {
        OrderCustomerDTO orderCustomerDTO = new OrderCustomerDTO();
        orderCustomerDTO.setOrderStatus(orderCustomerDTO.getOrderStatus());
        orderCustomerDTO.setQuantity(orderCustomerDTO.getQuantity());
        return orderCustomerDTO;
    }
}
