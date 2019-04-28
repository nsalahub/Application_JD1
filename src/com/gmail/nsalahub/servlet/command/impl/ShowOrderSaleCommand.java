package com.gmail.nsalahub.servlet.command.impl;

import com.gmail.nsalahub.reposetory.config.ConfiguratorManager;
import com.gmail.nsalahub.servises.OrderService;
import com.gmail.nsalahub.servises.impl.OrderServiceImpl;
import com.gmail.nsalahub.servises.model.sale.OrderSaleDTO;
import com.gmail.nsalahub.servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOrderSaleCommand implements Command {

    private ConfiguratorManager configuratorManager = ConfiguratorManager.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<OrderSaleDTO> order;
        Integer page;
        if (request.getParameter("page") == null) {
            order = orderService.showOrders(1);
        } else {
            page = Integer.valueOf(request.getParameter("page"));
            order = orderService.showOrders(page);
        }
        int valueOfPages = orderService.getValueOfPages();

        request.setAttribute("orders", order);
        request.setAttribute("pages", valueOfPages);
        return configuratorManager.getProperty(ConfiguratorManager.ORDER_SALE_PAGE_PATH);
    }
}
