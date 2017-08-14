package com.epam.services.impl;

import com.epam.domain.Profile;
import com.epam.dto.ProfileDto;
import com.epam.repository.ProfileRepository;
import com.epam.services.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileService implements IProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Profile createProfile(ProfileDto profileDto) {
        return profileRepository.save(conversionService.convert(profileDto, Profile.class));
    }

    @Override
    public Profile getUserByName(String name) {
        return profileRepository.findByName(name);
    }
}
