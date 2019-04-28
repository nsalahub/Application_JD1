package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.servises.model.UserDTO;

public interface UserConverter {
    User fromDTO(UserDTO userDTO);

    UserDTO toDTO(User user);
}
