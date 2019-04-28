package com.gmail.nsalahub.servises.converter.impl;

import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.servises.converter.UserConverter;
import com.gmail.nsalahub.servises.model.UserDTO;

public class UserConverterImpl implements UserConverter {

    private static UserConverterImpl instance;

    private UserConverterImpl() {
    }

    public static UserConverterImpl getInstance() {
        if (instance == null) {
            instance = new UserConverterImpl();
        }
        return instance;
    }

    private RoleConverterImpl roleConverter = RoleConverterImpl.getInstance();

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        user.setRole(roleConverter.fromDTO(userDTO.getRoleDTO()));
        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleDTO(roleConverter.toDTO(user.getRole()));
        return userDTO;
    }


}
