package com.gmail.nsalahub.servises.converter.impl.sale;

import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.reposetory.model.sale.ItemSale;
import com.gmail.nsalahub.servises.converter.ItemConverter;
import com.gmail.nsalahub.servises.converter.ItemSaleConverter;
import com.gmail.nsalahub.servises.converter.impl.ItemConverterImpl;
import com.gmail.nsalahub.servises.model.ItemDTO;
import com.gmail.nsalahub.servises.model.sale.ItemSaleDTO;

public class ItemSaleConverterImpl implements ItemSaleConverter {

    private static ItemSaleConverterImpl instance;

    private ItemSaleConverterImpl() {
    }

    public static ItemSaleConverterImpl getInstance(){
        if (instance == null){
            instance = new ItemSaleConverterImpl();
        }
        return instance;
    }
    @Override
    public ItemSale fromDTO(ItemSaleDTO itemSaleDTO) {
        ItemSale itemSale = new ItemSale();
        itemSale.setId(itemSale.getId());
        return itemSale;
    }

    @Override
    public ItemSaleDTO toDTO(ItemSale itemSale) {
        ItemSaleDTO itemSaleDTO = new ItemSaleDTO();
        itemSaleDTO.setId(itemSale.getId());
        return itemSaleDTO;
    }
}
