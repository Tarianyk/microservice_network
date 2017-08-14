package com.epam.services;

import com.epam.domain.Profile;
import com.epam.dto.ProfileDto;

public interface IProfileService {
    Profile createProfile(ProfileDto profileDto);

    Profile getUserByName(String name);
}
