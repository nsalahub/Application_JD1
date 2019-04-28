package com.gmail.nsalahub.servises.converter.impl;

import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.converter.RoleConverter;
import com.gmail.nsalahub.servises.converter.UserRegistrationConverter;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servises.model.UserRegistrationDTO;

public class UserRegistrationConverterImpl implements UserRegistrationConverter {

    private static UserRegistrationConverterImpl instance;

    private UserRegistrationConverterImpl() {
    }

    public static UserRegistrationConverterImpl getInstance() {
        if (instance == null) {
            instance = new UserRegistrationConverterImpl();
        }
        return instance;
    }

    private RoleConverter roleConverter = RoleConverterImpl.getInstance();

    @Override
    public UserRegistration fromDTO(UserRegistrationDTO userRegistrationDTO) {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setRole(roleConverter.fromDTO(userRegistrationDTO.getRoleDTO()));
        userRegistration.setEmail(userRegistration.getEmail());
        userRegistration.setName(userRegistration.getName());
        userRegistration.setSurname(userRegistration.getSurname());
        userRegistration.setPassword(userRegistration.getPassword());
        return userRegistration;
    }

    @Override
    public UserRegistrationDTO toDTO(UserRegistration userRegistration) {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setEmail(userRegistration.getEmail());
        userRegistrationDTO.setPassword(userRegistration.getPassword());
        userRegistrationDTO.setRoleDTO(roleConverter.toDTO(userRegistration.getRole()));
        userRegistrationDTO.setSurname(userRegistration.getSurname());
        userRegistrationDTO.setName(userRegistration.getName());
        return userRegistrationDTO;
    }
}
