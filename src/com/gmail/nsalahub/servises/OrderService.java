package com.gmail.nsalahub.servises;


import com.gmail.nsalahub.reposetory.model.OrderStatus;
import com.gmail.nsalahub.servises.model.customer.OrderCustomerDTO;
import com.gmail.nsalahub.servises.model.sale.OrderSaleDTO;

import java.util.List;

public interface OrderService {
    List<OrderSaleDTO> showOrders(Integer page);

    public void addRecordInOrder(OrderCustomerDTO orderCustomerDTO, long userId, String itemName);

    void updateRecordIntoOrderByUserId(OrderStatus orderStatus, long userId);

    void updateRecordIntoOrderByItemId(OrderStatus orderStatus, long itemId);

    Integer getValueOfPages();

}
