package com.gmail.nsalahub.reposetory.impl;

import com.gmail.nsalahub.reposetory.ItemRepository;
import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.reposetory.model.XMLItem;
import com.gmail.nsalahub.servises.exeptions.ItemException;
import com.gmail.nsalahub.servises.model.ItemDTO;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    private static ItemRepositoryImpl instance;

    private ItemRepositoryImpl() {
    }

    public static ItemRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ItemRepositoryImpl();
        }
        return instance;
    }


    @Override
    public void insertXMLRecordIntoItem(Connection connection, XMLItem xmlItem) throws ItemException {
        String insertTableSql = "INSERT INTO item VALUES (NULL ,?,?,?,?,false);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableSql)) {
            System.out.println("---------------fill xml item-------------------");
            preparedStatement.setString(1, xmlItem.getName());
            preparedStatement.setString(2, xmlItem.getDescription());
            preparedStatement.setString(3, xmlItem.getUniqueNumber());
            preparedStatement.setBigDecimal(4, xmlItem.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("insert is fallen---");
            e.printStackTrace();
            e.getMessage();
            throw new ItemException(e);
        }
    }

    @Override
    public void insertRecordIntoItem(Connection connection, Item item) throws ItemException {
        String insertTableSql = "INSERT INTO item VALUES (NULL ,?,?,?,?,false);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableSql)) {
            System.out.println("---------------fill item-------------------");
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getUniqueNumber());
            preparedStatement.setBigDecimal(4, item.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("insert is fallen---");
            e.printStackTrace();
            e.getMessage();
            throw new ItemException(e);
        }
    }

    @Override
    public List<Item> getItemFromDatabase(Connection connection, Integer page, Integer itemLimit) throws ItemException {
        String selectAllItem = "SELECT id, name, description, unique_number, price FROM item  WHERE deleted=FALSE LIMIT ? OFFSET ?;";
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllItem)) {
            preparedStatement.setInt(1, itemLimit);
            preparedStatement.setInt(2, (page - 1) * itemLimit);
            System.out.println("-----------Select All Item--------");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = getItem(resultSet);
                    itemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("select Item fallen-----------");
            e.getMessage();
            throw new ItemException(e);
        }
        return itemList;
    }

    @Override
    public Integer getValueItems(Connection connection) throws ItemException {
        String sqlRequest = "SELECT COUNT(*) AS value_pages FROM item WHERE deleted=false";
        int valueItems = 0;
        try (Statement statement = connection.createStatement()) {
            System.out.println("------------get pages repository--------------");
            try (ResultSet resultSet = statement.executeQuery(sqlRequest)) {
                while (resultSet.next()) {
                    valueItems = resultSet.getInt("value_pages");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new ItemException(e);
        }
        return valueItems;
    }

    @Override
    public List<Item> getItemByName(Connection connection, String itemName) throws ItemException {
        String sqlRequest = "SELECT id, name, description, unique_number, price FROM item WHERE name=?;";
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setString(1, itemName);
            System.out.println("-----------Checked item--------");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = getItem(resultSet);
                    itemList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("select Item fallen-----------");
            System.out.println(e.getMessage());
            throw new ItemException(e);
        }
        return itemList;
    }

    @Override
    public void updateRecordIntoItem(Connection connection, Item item , String updatesItemName) throws ItemException {
        String updateTableSql = "UPDATE item SET name=?, description=?, unique_number=?, price=? WHERE name=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateTableSql)) {
            System.out.println("----------Update item--------------");
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getUniqueNumber());
            preparedStatement.setBigDecimal(4, item.getPrice());
            preparedStatement.setString(5, updatesItemName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update is fallen------------");
            System.out.println(e.getMessage());
            throw new ItemException(e);
        }
    }

    @Override
    public void deleteRecordIntoItem(Connection connection, String name) throws ItemException {
        String deleteItemSql = "UPDATE item SET deleted=true WHERE name = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteItemSql)) {
            System.out.println("-------delete item-------");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("delete if fallen---------");
            System.out.println(e.getMessage());
            throw new ItemException(e);

        }
    }


    private Item getItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getLong("ID"));
        item.setName(resultSet.getString("NAME"));
        item.setUniqueNumber(resultSet.getString("UNIQUE_NUMBER"));
        item.setDescription(resultSet.getString("DESCRIPTION"));
        item.setPrice(resultSet.getBigDecimal("PRICE"));
        return item;
    }
}
