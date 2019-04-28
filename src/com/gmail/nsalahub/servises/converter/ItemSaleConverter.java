package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.sale.ItemSale;
import com.gmail.nsalahub.servises.model.sale.ItemSaleDTO;

public interface ItemSaleConverter {

    ItemSale fromDTO(ItemSaleDTO itemSaleDTO);

    ItemSaleDTO toDTO(ItemSale itemSale);
}
