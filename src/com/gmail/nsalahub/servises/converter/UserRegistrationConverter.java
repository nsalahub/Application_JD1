package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servises.model.UserRegistrationDTO;

public interface UserRegistrationConverter {

    UserRegistrationDTO toDTO(UserRegistration userRegistration);

    UserRegistration fromDTO(UserRegistrationDTO userRegistrationDTO);
}
