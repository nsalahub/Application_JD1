package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.customer.OrderCustomer;
import com.gmail.nsalahub.servises.model.customer.OrderCustomerDTO;

public interface OrderCustomerConverter {

    OrderCustomer fromDTO (OrderCustomerDTO orderCustomerDTO);

    OrderCustomerDTO toDTO (OrderCustomer orderCustomer);
}
