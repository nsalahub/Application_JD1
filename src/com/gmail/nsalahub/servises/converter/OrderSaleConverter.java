package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.sale.OrderSale;
import com.gmail.nsalahub.servises.model.sale.OrderSaleDTO;

public interface OrderSaleConverter {
    OrderSale fromDTO(OrderSaleDTO orderSaleDTO);

    OrderSaleDTO toDTO(OrderSale orderSale);
}
