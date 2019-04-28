package com.gmail.nsalahub.reposetory.impl;

import com.gmail.nsalahub.reposetory.RegistrationRepository;
import com.gmail.nsalahub.reposetory.model.Profile;
import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.exeptions.RegistrationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationRepositoryImpl implements RegistrationRepository {

    private static RegistrationRepositoryImpl instance;

    private RegistrationRepositoryImpl() {
    }

    public static RegistrationRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new RegistrationRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void saveUser(Connection connection, UserRegistration userRegistration) throws RegistrationException {
        String insertTableSql = "INSERT INTO users VALUES (null ,?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableSql)) {
            System.out.println("---------------fill users-------------------");

            preparedStatement.setString(1, userRegistration.getEmail());
            preparedStatement.setString(2, userRegistration.getSurname());
            preparedStatement.setString(3, userRegistration.getName());
            preparedStatement.setString(4, userRegistration.getPassword());
            preparedStatement.setLong(5, userRegistration.getRole().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("insert is fallen--------");
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RegistrationException(e);
        }
    }

    @Override
    public void saveProfile(Connection connection, Profile profile, String userEmail) throws RegistrationException {
        String insertTableSql = "INSERT INTO profile VALUES ((SELECT id FROM Users WHERE email = ?),?,?); ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableSql)) {
            System.out.println("------------Insert into Profile-------------");
            preparedStatement.setString(1,userEmail);
            preparedStatement.setString(2,profile.getAddress());
            preparedStatement.setString(3,profile.getTelephone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("insert into profile failed");
            e.printStackTrace();
            throw new RegistrationException(e);
        }
    }


}
