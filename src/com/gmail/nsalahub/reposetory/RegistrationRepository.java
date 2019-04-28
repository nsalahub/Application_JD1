package com.gmail.nsalahub.reposetory;

import com.gmail.nsalahub.reposetory.model.Profile;
import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.exeptions.RegistrationException;
import com.gmail.nsalahub.servises.model.ItemDTO;
import com.gmail.nsalahub.servises.model.UserDTO;

import java.sql.Connection;

public interface RegistrationRepository {

    void saveUser(Connection connection, UserRegistration user) throws RegistrationException;

    void saveProfile (Connection connection, Profile profile, String userEmail) throws RegistrationException;
}
