package com.gmail.nsalahub.servlet.command.impl;

import com.gmail.nsalahub.reposetory.config.ConfiguratorManager;
import com.gmail.nsalahub.servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowXMLCommand implements Command {

    private ConfiguratorManager configuratorManager = ConfiguratorManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return configuratorManager.getProperty(ConfiguratorManager.XML_PAGE_PATH);
    }
}
