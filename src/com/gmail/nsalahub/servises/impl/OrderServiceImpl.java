package com.gmail.nsalahub.servises.impl;


import com.gmail.nsalahub.reposetory.impl.OrderRepositoryImpl;
import com.gmail.nsalahub.reposetory.model.OrderStatus;
import com.gmail.nsalahub.reposetory.model.customer.OrderCustomer;
import com.gmail.nsalahub.reposetory.model.sale.OrderSale;
import com.gmail.nsalahub.servises.OrderService;
import com.gmail.nsalahub.servises.converter.OrderCustomerConverter;
import com.gmail.nsalahub.servises.converter.OrderSaleConverter;
import com.gmail.nsalahub.servises.converter.impl.customer.OrderCustomerConverterImpl;
import com.gmail.nsalahub.servises.converter.impl.sale.OrderSaleConverterImpl;
import com.gmail.nsalahub.servises.exeptions.OrderException;
import com.gmail.nsalahub.servises.model.customer.OrderCustomerDTO;
import com.gmail.nsalahub.servises.model.sale.OrderSaleDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static OrderServiceImpl instance;

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    private static final int LIMIT_ORDERS = 5;

    private ConnectionServiceImpl connectionService = ConnectionServiceImpl.getInstance();
    private OrderRepositoryImpl orderRepository = OrderRepositoryImpl.getInstance();
    private OrderSaleConverter orderSaleConverter = OrderSaleConverterImpl.getInstance();
    private OrderCustomerConverter orderCustomerConverter = OrderCustomerConverterImpl.getInstance();

    @Override
    public void addRecordInOrder(OrderCustomerDTO orderCustomerDTO, long userId, String itemName) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("------------Start insert into order-------------");
                connection.setAutoCommit(false);
                OrderCustomer orderCustomer = orderCustomerConverter.fromDTO(orderCustomerDTO);
                orderRepository.insertOrder(connection, orderCustomer, userId, itemName);
                connection.commit();
            } catch (SQLException | OrderException e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println(e.getMessage());
                System.out.println("transaction insert into order failed------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("add record in order failed---------------------");
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecordIntoOrderByUserId(OrderStatus orderStatus, long userId) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("-----------Start updating--------------");
                connection.setAutoCommit(false);
                orderRepository.updateStatusByUserId(connection, orderStatus, userId);
                connection.commit();
            } catch (SQLException | OrderException e) {
                connection.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("update transaction status in in order failed---------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update record in Order failed---------------");
            e.printStackTrace();
        }
    }


    @Override
    public void updateRecordIntoOrderByItemId(OrderStatus orderStatus, long itemId) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("---------Start updating----------------");
                connection.setAutoCommit(false);
                orderRepository.updateStatusByItemId(connection, orderStatus, itemId);
                connection.commit();
            } catch (SQLException | OrderException e) {
                connection.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("update transaction status in in order failed---------------");
            }
        } catch (SQLException e) {
            System.out.println("update record is failed-------------------");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderSaleDTO> showOrders(Integer page) {
        List<OrderSaleDTO> orders = new ArrayList<>();
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("---------------Start show string----------");
                connection.setAutoCommit(false);
                List<OrderSale> orderList = orderRepository.getOrders(connection, page, LIMIT_ORDERS);
                for (OrderSale order : orderList) {
                    orders.add(orderSaleConverter.toDTO(order));
                }
                connection.commit();
            } catch (SQLException | OrderException e) {
                connection.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("show transaction Orders is failed------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("show order is failed----------------");
        }
        return orders;
    }

    @Override
    public Integer getValueOfPages() {
        System.out.println("----------Value of pages order service -----------");
        Integer valueOfPages = 0;
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                System.out.println("------------Start getting orders pages----------");
                Integer orders = orderRepository.getValueOrders(connection);
                if (orders % LIMIT_ORDERS != 0) {
                    valueOfPages = (orders / LIMIT_ORDERS) + 1;
                } else valueOfPages = orders / LIMIT_ORDERS;
                connection.commit();
            } catch (SQLException | OrderException e) {
                connection.rollback();
                e.printStackTrace();
                System.out.println("----------getting pages problem transaction---------");
            }
        } catch (SQLException e) {
            System.out.println("------------getting pages problem--------");
            e.printStackTrace();
        }
        return valueOfPages;
    }

}
