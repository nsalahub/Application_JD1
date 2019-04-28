package com.gmail.nsalahub.servlet.model;

public enum CommandEnum {
    READXML,
    SHOWLOGIN,
    LOGIN,
    BUYIT,
    SHOWXML,
    DELETEITEM,
    REGISTRATION,
    ADDITEM,
    UPDATEITEM,
    SHOWORDERSALE,
    SHOWREGISTRATION,
    SHOWADDITEM,
    SHOWUPDATEITEM,
    SHOWITEM,
    SHOWITEMSALE;

    public static CommandEnum getCommand(String command) {
        try {
            return CommandEnum.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Command does not found");
            e.printStackTrace();
        }
        return null;
    }

}
