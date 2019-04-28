package com.gmail.nsalahub.servlet.command.impl;

import com.gmail.nsalahub.reposetory.config.ConfiguratorManager;
import com.gmail.nsalahub.servises.converter.UserConverter;
import com.gmail.nsalahub.servises.converter.impl.UserConverterImpl;
import com.gmail.nsalahub.servises.impl.LoginServiceImpl;
import com.gmail.nsalahub.servises.model.AuthenticationUserDTO;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servlet.command.Command;
import com.gmail.nsalahub.servlet.model.CommandEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginCommand implements Command {

    private static LoginCommand instance;

    public LoginCommand() {
    }

    public static LoginCommand getInstance() {
        if (instance == null) {
            instance = new LoginCommand();
        }
        return instance;
    }

    private LoginServiceImpl loginService = LoginServiceImpl.getInstance();
    private ConfiguratorManager configuratorManager = ConfiguratorManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDTO userLogin = loginService.findUser(email);
        if (email != null && !email.equals("")) {
            if (userLogin != null) {
                if (userLogin.getPassword().equals(password.trim())) {
                    HttpSession session = request.getSession();
                    AuthenticationUserDTO authenticationUserDTO = new AuthenticationUserDTO();
                    authenticationUserDTO.setPermissionEnum(userLogin.getRoleDTO().getPermission().get(0));
                    session.setAttribute("user", authenticationUserDTO);
                    String command = "/app/dispatcher?command=";
                    switch (userLogin.getRoleDTO().getPermission().get(0)) {
                        case CUSTOMER:
                            response.sendRedirect(command + CommandEnum.SHOWITEM.name().toLowerCase());
                            break;
                        case SALES:
                            response.sendRedirect(command + CommandEnum.SHOWITEMSALE.name().toLowerCase());
                            break;
                        default:
                            response.sendRedirect(command + CommandEnum.LOGIN.name().toLowerCase());
                    }
                    return null;
                } else {
                    request.setAttribute("error", "Password or username mismatch");
                    return configuratorManager.getProperty(ConfiguratorManager.LOGIN_PAGE_PATH);
                }
            } else {
                request.setAttribute("error", "Password or username mismatch");
                return configuratorManager.getProperty(ConfiguratorManager.LOGIN_PAGE_PATH);
            }
        } else {
            return configuratorManager.getProperty(ConfiguratorManager.LOGIN_PAGE_PATH);
        }
    }
}
