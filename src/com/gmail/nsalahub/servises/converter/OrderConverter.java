package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.Order;
import com.gmail.nsalahub.servises.model.OrderDTO;

public interface OrderConverter {
    Order fromDTO(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);
}
