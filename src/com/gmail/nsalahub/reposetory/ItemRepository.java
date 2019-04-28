package com.gmail.nsalahub.reposetory;

import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.reposetory.model.XMLItem;
import com.gmail.nsalahub.servises.exeptions.ItemException;
import com.gmail.nsalahub.servises.model.ItemDTO;

import java.sql.Connection;
import java.util.List;

public interface ItemRepository {

    void insertXMLRecordIntoItem(Connection connection, XMLItem xmlItem) throws ItemException;

    void updateRecordIntoItem(Connection connection, Item item , String updatesItemName) throws ItemException;

    void deleteRecordIntoItem(Connection connection, String name) throws ItemException;

    void insertRecordIntoItem(Connection connection, Item item) throws ItemException;

    List<Item> getItemFromDatabase(Connection connection, Integer page,Integer itemLimit) throws ItemException;

    Integer getValueItems(Connection connection) throws ItemException;

    List<Item> getItemByName(Connection connection,  String itemName) throws ItemException;

}
