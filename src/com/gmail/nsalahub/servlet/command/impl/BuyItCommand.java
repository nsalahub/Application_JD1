package com.gmail.nsalahub.servlet.command.impl;

import com.gmail.nsalahub.reposetory.config.ConfiguratorManager;
import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.reposetory.model.OrderStatus;
import com.gmail.nsalahub.servises.OrderService;
import com.gmail.nsalahub.servises.impl.OrderServiceImpl;
import com.gmail.nsalahub.servises.model.ItemDTO;
import com.gmail.nsalahub.servises.model.OrderDTO;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servises.model.customer.OrderCustomerDTO;
import com.gmail.nsalahub.servises.model.sale.OrderSaleDTO;
import com.gmail.nsalahub.servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyItCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();
    private ConfiguratorManager configuratorManager = ConfiguratorManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String quantity = request.getParameter("quantity");
        String itemIdString = request.getParameter("name");
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        OrderCustomerDTO orderCustomerDTO = new OrderCustomerDTO();
        orderCustomerDTO.setQuantity(Integer.parseInt(quantity));
        orderCustomerDTO.setOrderStatus(OrderStatus.NEW);
        orderService.addRecordInOrder(orderCustomerDTO, userDTO.getId(),itemIdString);
        return configuratorManager.getProperty(ConfiguratorManager.SUCCESS_PAGE_PATH);
    }
}
