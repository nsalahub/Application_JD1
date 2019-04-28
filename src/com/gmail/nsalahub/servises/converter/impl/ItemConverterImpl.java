package com.gmail.nsalahub.servises.converter.impl;

import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.servises.converter.ItemConverter;
import com.gmail.nsalahub.servises.model.ItemDTO;

public class ItemConverterImpl implements ItemConverter {

    private static ItemConverterImpl instance;

    private ItemConverterImpl() {
    }

    public static ItemConverterImpl getInstance() {
        if (instance == null) {
        instance = new ItemConverterImpl();
        }
        return instance;
    }

    @Override
    public Item fromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setUniqueNumber(itemDTO.getUniqueNumber());
        item.setPrice(itemDTO.getPrice());
        return item;
    }

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setUniqueNumber(item.getUniqueNumber());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }


}
