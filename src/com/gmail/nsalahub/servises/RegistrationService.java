package com.gmail.nsalahub.servises;

import com.gmail.nsalahub.reposetory.model.Profile;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.model.ProfileDTO;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servises.model.UserRegistrationDTO;

public interface RegistrationService {
    void prepareRegistration(UserRegistrationDTO userRegistrationDTO, ProfileDTO profileDTO);
}
