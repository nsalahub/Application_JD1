package com.gmail.nsalahub.servises.impl;

import com.gmail.nsalahub.reposetory.LoginRepository;
import com.gmail.nsalahub.reposetory.impl.LoginRepositoryImpl;
import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.ConnectionService;
import com.gmail.nsalahub.servises.LoginService;
import com.gmail.nsalahub.servises.converter.UserConverter;
import com.gmail.nsalahub.servises.converter.UserRegistrationConverter;
import com.gmail.nsalahub.servises.converter.impl.UserConverterImpl;
import com.gmail.nsalahub.servises.converter.impl.UserRegistrationConverterImpl;
import com.gmail.nsalahub.servises.exeptions.LoginException;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servises.model.UserRegistrationDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    private static LoginServiceImpl instance;

    private LoginServiceImpl() {
    }

    public static LoginServiceImpl getInstance() {
        if (instance == null) {
            instance = new LoginServiceImpl();
        }
        return instance;
    }

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();
    private LoginRepository authorizationRepository = LoginRepositoryImpl.getInstance();
    private UserRegistrationConverter userConverter = UserRegistrationConverterImpl.getInstance();

    @Override
    public UserRegistrationDTO findUser(String email) {
        UserRegistrationDTO userDTO = null;
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("-----------------Start validation password--------------");
                connection.setAutoCommit(false);
                UserRegistration userRegistration = authorizationRepository.getUserByEmail(connection, email);
                userDTO = userConverter.toDTO(userRegistration);

                connection.commit();
            } catch (SQLException | LoginException e) {
                connection.rollback();
                System.out.println(e.getMessage());
                System.out.println("Password authorization service failed-----------------------");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Password authorization service problem-------------------");
            e.printStackTrace();
        }
        return userDTO;
    }

}
