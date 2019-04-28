package com.gmail.nsalahub.reposetory.impl;

import com.gmail.nsalahub.reposetory.OrderRepository;
import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.reposetory.model.Order;
import com.gmail.nsalahub.reposetory.model.OrderStatus;
import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.customer.OrderCustomer;
import com.gmail.nsalahub.reposetory.model.sale.ItemSale;
import com.gmail.nsalahub.reposetory.model.sale.OrderSale;
import com.gmail.nsalahub.reposetory.model.sale.UserSale;
import com.gmail.nsalahub.servises.exeptions.LoginException;
import com.gmail.nsalahub.servises.exeptions.OrderException;
import com.mysql.cj.jdbc.result.ResultSetFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private static OrderRepositoryImpl instance;

    private OrderRepositoryImpl() {
    }

    public static OrderRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImpl();
        }
        return instance;
    }


    @Override
    public void insertOrder(Connection connection, OrderCustomer order, long userId, String itemName) throws OrderException {
        String sqlRequest = "INSERT INTO `order` VALUES (NULL ,?,(SELECT id FROM item WHERE name =?),NOW(),?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            System.out.println("----------INSERT INTO order values-------------");
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, itemName);
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setString(4, String.valueOf(order.getOrderStatus()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert failed--------------");
            e.printStackTrace();
            throw new OrderException(e);
        }
    }

    @Override
    public void updateStatusByUserId(Connection connection, OrderStatus orderStatus, long userId) throws OrderException {
        String sqlRequest = "UPDATE `order` SET state=? WHERE user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            System.out.println("----------------Update state WHERE user ID----------------");
            preparedStatement.setString(1, String.valueOf(orderStatus));
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update state by user id is failed-------------------");
            e.printStackTrace();
            throw new OrderException(e);
        }
    }

    @Override
    public void updateStatusByItemId(Connection connection, OrderStatus orderStatus, long itemId) throws OrderException {
        String sqlRequest = "UPDATE `order` SET state=? WHERE item_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            System.out.println("----------------Update state WHERE item ID----------------");
            preparedStatement.setString(1, String.valueOf(orderStatus));
            preparedStatement.setLong(2, itemId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update state by item id is failed--------------");
            e.printStackTrace();
            throw new OrderException(e);
        }
    }

    @Override
    public List<OrderSale> getOrders(Connection connection, Integer page, Integer orderLimit) throws OrderException {
        List<OrderSale> orders = new ArrayList<>();
        String sqlRequest = "SELECT id, user_id, item_id, created, quantity, state FROM `order` LIMIT ? OFFSET ? ;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            System.out.println("--------------Start select limit from order-------------------");
            preparedStatement.setLong(1, orderLimit);
            preparedStatement.setInt(2, (page - 1) * orderLimit);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OrderSale order = getOrder(resultSet);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("-------Select from order failed---------------");
            e.printStackTrace();
            throw new OrderException(e);
        }
        return orders;
    }

    @Override
    public Integer getValueOrders(Connection connection) throws OrderException {
        String sqlRequest = "SELECT COUNT(*) AS value_pages FROM `order`";
        int valueOrders = 0;
        try (Statement statement = connection.createStatement()) {
            System.out.println("------------get pages Order repository--------------");
            try (ResultSet resultSet = statement.executeQuery(sqlRequest)) {
                while (resultSet.next()) {
                    valueOrders = resultSet.getInt("value_pages");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new OrderException(e);
        }
        return valueOrders;
    }

    private OrderSale getOrder(ResultSet resultSet) throws SQLException {
        OrderSale order = new OrderSale();
        order.setId(resultSet.getLong("id"));
        UserSale user = new UserSale();
        user.setId(resultSet.getLong("user_id"));
        order.setUser(user);
        ItemSale item = new ItemSale();
        item.setId(resultSet.getLong("item_id"));
        order.setItem(item);
        Date creationDate = new Date(resultSet.getTimestamp("created").getTime());
        order.setCreateDate(creationDate);
        order.setQuantity(resultSet.getInt("quantity"));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("state")));
        return order;
    }
}
