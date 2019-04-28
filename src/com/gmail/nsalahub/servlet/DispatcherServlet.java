package com.gmail.nsalahub.servlet;

import com.gmail.nsalahub.servises.BaseCreatorService;
import com.gmail.nsalahub.servises.impl.BaseCreatorServiceImpl;
import com.gmail.nsalahub.servlet.command.Command;
import com.gmail.nsalahub.servlet.command.impl.*;
import com.gmail.nsalahub.servlet.model.CommandEnum;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private static final Map<CommandEnum, Command> commands = new HashMap();
    private BaseCreatorService baseCreatorService = BaseCreatorServiceImpl.getInstance();

    @Override
    public void init() {
        baseCreatorService.createTables();
        System.out.println("DispatcherServlet Init!");
        commands.put(CommandEnum.SHOWREGISTRATION, new ShowRegistrationCommand());
        commands.put(CommandEnum.LOGIN, new LoginCommand());
        commands.put(CommandEnum.SHOWADDITEM, new ShowAddItemCommand());
        commands.put(CommandEnum.SHOWITEM, new ShowItemCommand());
        commands.put(CommandEnum.SHOWITEMSALE, new ShowItemSaleCommand());
        commands.put(CommandEnum.SHOWUPDATEITEM, new ShowUpdateItemCommand());
        commands.put(CommandEnum.REGISTRATION, new RegistrationCommand());
        commands.put(CommandEnum.ADDITEM, new AddItemCommand());
        commands.put(CommandEnum.UPDATEITEM, new UpdateItemCommand());
        commands.put(CommandEnum.SHOWORDERSALE, new ShowOrderSaleCommand());
        commands.put(CommandEnum.DELETEITEM, new DeleteItemCommand());
        commands.put(CommandEnum.BUYIT, new BuyItCommand());
        commands.put(CommandEnum.SHOWLOGIN, new ShowLoginCommand());
        commands.put(CommandEnum.SHOWXML, new ShowXMLCommand());
        commands.put(CommandEnum.READXML, new ReadXMLCommand());
    }

    @Override
    public void destroy() {
        System.out.println("DispatcherServlet Destroy!");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        System.out.println("Command" + " " + command + " " + "getting from request");
        Command commandAction = null;
        try {
            commandAction = commands.get(CommandEnum.getCommand(command));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (commandAction != null) {
            try {
                String page = commandAction.execute(request, response);
                getServletContext().getRequestDispatcher(page).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Command" + command + "does not exists!");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}