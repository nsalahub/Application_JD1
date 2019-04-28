package com.gmail.nsalahub.servlet.command.impl;

import com.gmail.nsalahub.reposetory.config.ConfiguratorManager;
import com.gmail.nsalahub.servises.ItemService;
import com.gmail.nsalahub.servises.impl.ItemServiceImpl;
import com.gmail.nsalahub.servises.model.ItemDTO;
import com.gmail.nsalahub.servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowItemSaleCommand implements Command {

    private ConfiguratorManager configuratorManager = ConfiguratorManager.getInstance();
    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ItemDTO> items;
        Integer page;
        if (request.getParameter("page") == null) {
            items = itemService.showItems(1);
        } else {
            page = Integer.valueOf(request.getParameter("page"));
            items = itemService.showItems(page);
        }
        int valueOfPages = itemService.getValueOfPages();
        request.setAttribute("items", items);
        request.setAttribute("pages", valueOfPages);

        return configuratorManager.getProperty(ConfiguratorManager.ITEM_SALE_PAGE_PATH);
    }
}
