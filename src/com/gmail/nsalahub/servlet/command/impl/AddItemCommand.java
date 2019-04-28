package com.gmail.nsalahub.servlet.command.impl;

import com.gmail.nsalahub.reposetory.config.ConfiguratorManager;
import com.gmail.nsalahub.servises.ItemService;
import com.gmail.nsalahub.servises.impl.ItemServiceImpl;
import com.gmail.nsalahub.servises.model.ItemDTO;
import com.gmail.nsalahub.servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddItemCommand implements Command {

    private ConfiguratorManager configuratorManager = ConfiguratorManager.getInstance();
    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nameItem = request.getParameter("name-item");
        String descriptionItem = request.getParameter("description-item");
        String uniqueNumber = request.getParameter("uniqueNumber");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price-item")));
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(nameItem);
        itemDTO.setDescription(descriptionItem);
        itemDTO.setPrice(price);
        itemDTO.setUniqueNumber(uniqueNumber);
        if (itemService.isExistItem(itemDTO)){
            request.setAttribute("error", "This Item is exist already");
            return configuratorManager.getProperty(ConfiguratorManager.ADD_ITEM_PAGE_PATH);
        } else {
            itemService.insertItem(itemDTO);
            request.setAttribute("error", "Success added Item");
            return configuratorManager.getProperty(ConfiguratorManager.ADD_ITEM_PAGE_PATH);
        }
    }
}
