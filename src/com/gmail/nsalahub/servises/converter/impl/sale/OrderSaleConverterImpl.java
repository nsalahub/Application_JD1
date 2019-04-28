package com.gmail.nsalahub.servises.converter.impl.sale;

import com.gmail.nsalahub.reposetory.model.sale.OrderSale;
import com.gmail.nsalahub.servises.converter.ItemSaleConverter;
import com.gmail.nsalahub.servises.converter.OrderSaleConverter;
import com.gmail.nsalahub.servises.converter.UserSaleConverter;
import com.gmail.nsalahub.servises.model.sale.OrderSaleDTO;

public class OrderSaleConverterImpl implements OrderSaleConverter {

    private static OrderSaleConverterImpl instance;

    private OrderSaleConverterImpl() {
    }

    public static OrderSaleConverterImpl getInstance() {
        if (instance == null) {
            instance = new OrderSaleConverterImpl();
        }
        return instance;
    }

    private UserSaleConverter userSaleConverter = UserSaleConverterImpl.getInstance();
    private ItemSaleConverter itemSaleConverter = ItemSaleConverterImpl.getInstance();

    @Override
    public OrderSale fromDTO(OrderSaleDTO orderSaleDTO) {
        OrderSale orderSale = new OrderSale();
        orderSale.setId(orderSaleDTO.getId());
        orderSale.setCreateDate(orderSaleDTO.getCreateDate());
        orderSale.setItem(itemSaleConverter.fromDTO(orderSaleDTO.getItem()));
        orderSale.setUser(userSaleConverter.fromDTO(orderSaleDTO.getUser()));
        orderSale.setOrderStatus(orderSaleDTO.getOrderStatus());
        return orderSale;
    }

    @Override
    public OrderSaleDTO toDTO(OrderSale orderSale) {
        OrderSaleDTO orderSaleDTO = new OrderSaleDTO();
        orderSaleDTO.setId(orderSale.getId());
        orderSaleDTO.setCreateDate(orderSale.getCreateDate());
        orderSaleDTO.setOrderStatus(orderSale.getOrderStatus());
        orderSaleDTO.setUser(userSaleConverter.toDTO(orderSale.getUser()));
        orderSaleDTO.setItem(itemSaleConverter.toDTO(orderSale.getItem()));
        return orderSaleDTO;
    }
}
