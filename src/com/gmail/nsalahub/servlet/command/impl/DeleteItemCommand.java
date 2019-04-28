package com.gmail.nsalahub.servlet.command.impl;


import com.gmail.nsalahub.servises.ItemService;
import com.gmail.nsalahub.servises.impl.ItemServiceImpl;
import com.gmail.nsalahub.servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteItemCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        itemService.deleteItem(name);
        request.setAttribute("massage", "success delete item ");
        String url = "/app/dispatcher?command=";
        response.sendRedirect(url+"showitemsale");
        HttpSession session = request.getSession();
        String userDTO = (String) session.getAttribute("user");
        System.out.println(userDTO + "                                    'v'bk");
        return null;
    }
}
