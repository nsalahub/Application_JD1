package com.gmail.nsalahub.reposetory;

import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.exeptions.LoginException;

import java.sql.Connection;

public interface LoginRepository {

    UserRegistration getUserByEmail(Connection connection, String email) throws LoginException;
}
