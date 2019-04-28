package com.gmail.nsalahub.servises;

import com.gmail.nsalahub.servises.model.ItemDTO;
import com.gmail.nsalahub.servises.model.xml.XMLItemDTO;

import java.util.List;

public interface ItemService {

    void insertXMLItem(XMLItemDTO xmlItemDTO);

    void insertItem(ItemDTO itemDTO);

    void updateItem(ItemDTO itemDTO);

    void deleteItem(String itemName);

    List<ItemDTO> showItems(Integer page);

    Integer getValueOfPages();

    boolean isExistItem(ItemDTO itemDTO);
}
