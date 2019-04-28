package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.Profile;
import com.gmail.nsalahub.servises.model.ProfileDTO;

public interface ProfileConverter {

    Profile fromDTO(ProfileDTO profileDTO);

    ProfileDTO toDTO(Profile profile);
}
