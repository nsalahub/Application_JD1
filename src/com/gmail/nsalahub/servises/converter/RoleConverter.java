package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.Role;
import com.gmail.nsalahub.servises.model.RoleDTO;

public interface RoleConverter {

    Role fromDTO(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);
}
