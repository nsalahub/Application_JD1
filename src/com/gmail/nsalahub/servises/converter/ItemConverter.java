package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.Item;
import com.gmail.nsalahub.servises.model.ItemDTO;

public interface ItemConverter {
    Item fromDTO(ItemDTO itemDTO);

    ItemDTO toDTO(Item item);

}
