package com.gmail.nsalahub.servlet.command.impl;

import com.gmail.nsalahub.reposetory.config.ConfiguratorManager;
import com.gmail.nsalahub.reposetory.model.PermissionEnum;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.LoginService;
import com.gmail.nsalahub.servises.RegistrationService;
import com.gmail.nsalahub.servises.impl.RegistrationServiceImpl;
import com.gmail.nsalahub.servises.impl.LoginServiceImpl;
import com.gmail.nsalahub.servises.model.ProfileDTO;
import com.gmail.nsalahub.servises.model.RoleDTO;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servises.model.UserRegistrationDTO;
import com.gmail.nsalahub.servlet.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RegistrationCommand implements Command {

    private ConfiguratorManager configuratorManager = ConfiguratorManager.getInstance();
    private LoginService loginService = LoginServiceImpl.getInstance();
    private RegistrationService registrationService = RegistrationServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        ProfileDTO profileDTO = new ProfileDTO();
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setName(name);
        userRegistrationDTO.setEmail(email);
        userRegistrationDTO.setPassword(password);
        userRegistrationDTO.setSurname(surname);
        RoleDTO roleDTO = new RoleDTO();
        List<PermissionEnum> permissionEnums = new ArrayList<>();
        permissionEnums.add(PermissionEnum.CUSTOMER);
        roleDTO.setId(2);
        roleDTO.setName("CUSTOMER");
        roleDTO.setPermission(permissionEnums);
        profileDTO.setAddress(address);
        profileDTO.setTelephone(telephone);
        profileDTO.setUserDTO(userRegistrationDTO);
        userRegistrationDTO.setRoleDTO(roleDTO);

        if (email != null && !email.equals("")) {
            if (loginService.findUser(email) == null) {
                registrationService.prepareRegistration(userRegistrationDTO, profileDTO);
                request.setAttribute("error","Success created customer");
                return configuratorManager.getProperty(ConfiguratorManager.LOGIN_PAGE_PATH);
            } else {
                request.setAttribute("error", "Sorry, this email is already, please try entered your data again");
                return configuratorManager.getProperty(ConfiguratorManager.REGISTRATION_PAGE_PATH);
            }
        } else {
            request.setAttribute("error", "Please, entered your email correct");
            return configuratorManager.getProperty(ConfiguratorManager.REGISTRATION_PAGE_PATH);
        }
    }
}
