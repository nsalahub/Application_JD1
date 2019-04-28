package com.gmail.nsalahub.reposetory;

import com.gmail.nsalahub.reposetory.model.Profile;
import com.gmail.nsalahub.reposetory.model.User;

import java.sql.Connection;

public interface ProfileRepository {

    public void updatePasswordIntoUsers(Connection connection, String password, String email);

    void updateInfoIntoUsers(Connection connection, User user);

    void updateInfoIntoProfile(Connection connection);

    void updateInfoIntoProfile(Connection connection, Profile profile);
}
