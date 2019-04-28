package com.gmail.nsalahub.filters;

import com.gmail.nsalahub.reposetory.model.PermissionEnum;
import com.gmail.nsalahub.servises.model.AuthenticationUserDTO;
import com.gmail.nsalahub.servlet.model.CommandEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationFilter implements Filter {

    private static final Set<CommandEnum> COMMAND_FOR_EVERYONE = new HashSet<>();
    private static final Set<CommandEnum> SALE_USER = new HashSet<>();
    private static final Set<CommandEnum> CUSTOMER_USER = new HashSet<>();
    private static final String LOGIN_PAGE_PATH = "/index.jsp";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------------Authorization started-------------");
        COMMAND_FOR_EVERYONE.add(CommandEnum.REGISTRATION);
        COMMAND_FOR_EVERYONE.add(CommandEnum.LOGIN);
        COMMAND_FOR_EVERYONE.add(CommandEnum.SHOWREGISTRATION);
        COMMAND_FOR_EVERYONE.add(CommandEnum.SHOWLOGIN);
        SALE_USER.add(CommandEnum.SHOWITEMSALE);
        SALE_USER.add(CommandEnum.SHOWORDERSALE);
        SALE_USER.add(CommandEnum.SHOWUPDATEITEM);
        SALE_USER.add(CommandEnum.DELETEITEM);
        SALE_USER.add(CommandEnum.UPDATEITEM);
        SALE_USER.add(CommandEnum.ADDITEM);
        SALE_USER.add(CommandEnum.SHOWADDITEM);
        SALE_USER.add(CommandEnum.SHOWXML);
        SALE_USER.add(CommandEnum.READXML);
        CUSTOMER_USER.add(CommandEnum.BUYIT);
        CUSTOMER_USER.add(CommandEnum.SHOWITEM);

    }

    @Override
    public void destroy(){
        System.out.println("Filter destroy!");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        String command = req.getParameter("command");
        if (session == null) {
            defaultFilter(servletRequest, servletResponse, filterChain, req, resp, command);
            System.out.println(command + " тууууут");
        } else {
            AuthenticationUserDTO authenticationUserDTO = (AuthenticationUserDTO) session.getAttribute("user");
            if (authenticationUserDTO == null) {
                defaultFilter(servletRequest, servletResponse, filterChain, req, resp, command);
            } else {
                CommandEnum commandEnum = CommandEnum.getCommand(command);
                System.out.println(command + " is here");
                PermissionEnum permissionEnum = authenticationUserDTO.getPermissionEnum();
                switch (permissionEnum) {
                    case CUSTOMER:
                        if (CUSTOMER_USER.contains(commandEnum)) {
                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            session.removeAttribute("user");
                            resp.sendRedirect(req.getContextPath() + LOGIN_PAGE_PATH);
                        }
                        break;
                    case SALES:
                        if (SALE_USER.contains(commandEnum)) {
                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            session.removeAttribute("user");
                            resp.sendRedirect(req.getContextPath() + LOGIN_PAGE_PATH);
                        }
                        break;
                    default:
                        session.removeAttribute("user");
                        resp.sendRedirect(req.getContextPath() + LOGIN_PAGE_PATH);
                }
            }
        }

    }

    private void defaultFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                               FilterChain filterChain, HttpServletRequest req, HttpServletResponse resp,
                               String command) throws IOException, ServletException {
        if (req.getMethod().equals("POST")) {
            CommandEnum commandEnum = CommandEnum.getCommand(command);
            if (COMMAND_FOR_EVERYONE.contains(commandEnum)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                resp.sendRedirect(req.getContextPath() + LOGIN_PAGE_PATH);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + LOGIN_PAGE_PATH);
        }
    }


}
