package com.gmail.nsalahub.servises.impl;

import com.gmail.nsalahub.reposetory.impl.ItemRepositoryImpl;
import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.reposetory.model.XMLItem;
import com.gmail.nsalahub.servises.ItemService;
import com.gmail.nsalahub.servises.converter.XMLItemConverter;
import com.gmail.nsalahub.servises.converter.impl.ItemConverterImpl;
import com.gmail.nsalahub.servises.converter.impl.XMLItemConverterImpl;
import com.gmail.nsalahub.servises.exeptions.ItemException;
import com.gmail.nsalahub.servises.model.ItemDTO;
import com.gmail.nsalahub.servises.model.xml.XMLItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static ItemServiceImpl instance;

    private ItemServiceImpl() {
    }

    public static ItemServiceImpl getInstance() {
        if (instance == null) {
            instance = new ItemServiceImpl();
        }
        return instance;
    }

    ItemRepositoryImpl itemRepository = ItemRepositoryImpl.getInstance();
    ConnectionServiceImpl connectionService = ConnectionServiceImpl.getInstance();
    ItemConverterImpl itemConverter = ItemConverterImpl.getInstance();
    XMLItemConverter xmlItemConverter = XMLItemConverterImpl.getInstance();

    private static final int LIMIT_ITEMS = 5;

    @Override
    public void insertXMLItem(XMLItemDTO xmlItemDTO) {
        System.out.println("------------------Insert XML items------------");
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                System.out.println("------------service xml--------");
                XMLItem xmlItem = xmlItemConverter.fromDTO(xmlItemDTO);
                itemRepository.insertXMLRecordIntoItem(connection, xmlItem);
            } catch (SQLException | ItemException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw  new RuntimeException();
        }
    }


    @Override
    public void insertItem(ItemDTO itemDTO) {
        System.out.println("---------Insert Item Service-----------");
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                System.out.println("----------record in service----------");
                Item item = itemConverter.fromDTO(itemDTO);
                itemRepository.insertRecordIntoItem(connection, item);
                connection.commit();
            } catch (SQLException | ItemException e) {
                connection.rollback();
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("transaction failed----------");
            }
        } catch (SQLException e) {
            System.out.println("item service problem----------");
            e.printStackTrace();
            e.getMessage();
            throw new RuntimeException();
        }
    }


    @Override
    public void updateItem(ItemDTO itemDTO) {
        System.out.println("----------Item update----------");
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Item item = itemConverter.fromDTO(itemDTO);
                itemRepository.updateRecordIntoItem(connection, item, item.getName());
                connection.commit();
            } catch (SQLException | ItemException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                connection.rollback();
                System.out.println("Update transaction failed------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update is failed--------------");
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public void deleteItem(String itemName) {
        System.out.println("------------Delete Item---------");
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                itemRepository.deleteRecordIntoItem(connection, itemName);
                connection.commit();
            } catch (SQLException | ItemException e) {
                connection.rollback();
                System.out.println(e.getMessage());
                System.out.println("delete Item failed--------------");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("delete in service-----------");
            throw new RuntimeException();
        }
    }


    @Override
    public List<ItemDTO> showItems(Integer page) {
        System.out.println("------------Selected item-------------");
        List<ItemDTO> itemDTOList = new ArrayList<>();
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                System.out.println("-------------Select All Item-------");
                List<Item> itemList = itemRepository.getItemFromDatabase(connection, page, LIMIT_ITEMS);
                for (Item item : itemList) {
                    itemDTOList.add(itemConverter.toDTO(item));
                }
                connection.commit();
            } catch (SQLException | ItemException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Selected Item failed----------");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("select in service-----------");
            throw new RuntimeException();
        }
        return itemDTOList;
    }

    @Override
    public Integer getValueOfPages() {
        System.out.println("----------Value of pages service -----------");
        Integer valueOfPages = 0;
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                System.out.println("------------Start getting pages----------");
                Integer items = itemRepository.getValueItems(connection);
                if (items % LIMIT_ITEMS != 0) {
                    valueOfPages = (items / LIMIT_ITEMS) + 1;
                } else valueOfPages = items / LIMIT_ITEMS;
                connection.commit();
            } catch (SQLException | ItemException e) {
                connection.rollback();
                e.printStackTrace();
                System.out.println("----------getting pages problem transaction---------");
            }
        } catch (SQLException e) {
            System.out.println("------------getting pages problem--------");
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        return valueOfPages;
    }

    @Override
    public boolean isExistItem(ItemDTO itemDTO) {
        boolean isExist = false;
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                System.out.println("-------------Start checked is exists Item------------");
                try {
                    itemList = itemRepository.getItemByName(connection, itemDTO.getName());
                } catch (NullPointerException e) {
                    isExist = false;
                }
                if (itemList.size() != 0) {
                    isExist = true;
                }
                connection.commit();
            } catch (SQLException | ItemException e) {
                connection.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("-----------Checked is exist Item transaction fallen----------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("-------------Checked is exist fallen----------------");
            throw new RuntimeException();
        }
        return isExist;
    }


}
