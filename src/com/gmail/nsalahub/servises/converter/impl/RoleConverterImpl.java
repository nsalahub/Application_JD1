package com.gmail.nsalahub.servises.converter.impl;

import com.gmail.nsalahub.reposetory.model.Role;
import com.gmail.nsalahub.servises.converter.RoleConverter;
import com.gmail.nsalahub.servises.model.RoleDTO;

public class RoleConverterImpl implements RoleConverter {

    private static RoleConverterImpl instance;

    private RoleConverterImpl() {
    }

    public static RoleConverterImpl getInstance() {
        if (instance == null) {
            instance = new RoleConverterImpl();
        }
        return instance;
    }

    @Override
    public Role fromDTO(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setPermissionEnums(roleDTO.getPermission());
        role.setName(roleDTO.getName());
        return role;
    }

    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setPermission(role.getPermissionEnums());
        return roleDTO;
    }


}
