package com.gmail.nsalahub.servises.impl;

import com.gmail.nsalahub.reposetory.impl.RegistrationRepositoryImpl;
import com.gmail.nsalahub.reposetory.model.Profile;
import com.gmail.nsalahub.reposetory.model.User;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.RegistrationService;
import com.gmail.nsalahub.servises.converter.ProfileConverter;
import com.gmail.nsalahub.servises.converter.UserRegistrationConverter;
import com.gmail.nsalahub.servises.converter.impl.ProfileConverterImpl;
import com.gmail.nsalahub.servises.converter.impl.UserConverterImpl;
import com.gmail.nsalahub.servises.converter.impl.UserRegistrationConverterImpl;
import com.gmail.nsalahub.servises.exeptions.RegistrationException;
import com.gmail.nsalahub.servises.model.ProfileDTO;
import com.gmail.nsalahub.servises.model.UserDTO;
import com.gmail.nsalahub.servises.model.UserRegistrationDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationServiceImpl implements RegistrationService {

    private static RegistrationServiceImpl instance;

    private RegistrationServiceImpl() {
    }

    public static RegistrationServiceImpl getInstance() {
        if (instance == null) {
            instance = new RegistrationServiceImpl();
        }
        return instance;
    }

    private ConnectionServiceImpl connectionService = ConnectionServiceImpl.getInstance();
    private RegistrationRepositoryImpl registrationRepository = RegistrationRepositoryImpl.getInstance();
    private UserRegistrationConverter userRegistrationConverter = UserRegistrationConverterImpl.getInstance();
    private ProfileConverter profileConverter = ProfileConverterImpl.getInstance();

    @Override
    public void prepareRegistration(UserRegistrationDTO userRegistrationDTO, ProfileDTO profileDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                System.out.println("--------Start transaction Registration----------------");
                connection.setAutoCommit(false);
                UserRegistration userRegistration = userRegistrationConverter.fromDTO(userRegistrationDTO);
                Profile profile = profileConverter.fromDTO(profileDTO);
                registrationRepository.saveUser(connection, userRegistration);
                registrationRepository.saveProfile(connection, profile, userRegistration.getEmail());
                connection.commit();
            } catch (SQLException | RegistrationException e) {
                connection.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println("Transaction registration failed-----------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Registration failed--------------------");
            e.printStackTrace();
        }
    }
}
