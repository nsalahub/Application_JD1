package com.gmail.nsalahub.servises;

import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servises.model.UserRegistrationDTO;

public interface LoginService {


    UserRegistrationDTO findUser(String email);
}
