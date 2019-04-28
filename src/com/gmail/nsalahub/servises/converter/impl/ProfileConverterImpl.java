package com.gmail.nsalahub.servises.converter.impl;

import com.gmail.nsalahub.reposetory.model.Profile;
import com.gmail.nsalahub.reposetory.model.UserRegistration;
import com.gmail.nsalahub.servises.converter.ProfileConverter;
import com.gmail.nsalahub.servises.converter.UserConverter;
import com.gmail.nsalahub.servises.converter.UserRegistrationConverter;
import com.gmail.nsalahub.servises.model.ProfileDTO;

public class ProfileConverterImpl implements ProfileConverter {

    private static ProfileConverterImpl instance;

    private ProfileConverterImpl() {
    }

    private UserRegistrationConverter userRegistrationConverter = UserRegistrationConverterImpl.getInstance();

    public static ProfileConverterImpl getInstance() {
        if (instance == null) {
            instance = new ProfileConverterImpl();
        }
        return instance;
    }

    @Override
    public Profile fromDTO(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setUserRegistration(userRegistrationConverter.fromDTO(profileDTO.getUserDTO()));
        profile.setAddress(profileDTO.getAddress());
        profile.setTelephone(profileDTO.getTelephone());
        return profile;
    }

    @Override
    public ProfileDTO toDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUserDTO(userRegistrationConverter.toDTO(profile.getUserRegistration()));
        profileDTO.setAddress(profile.getAddress());
        profileDTO.setTelephone(profileDTO.getTelephone());
        return profileDTO;
    }
}
