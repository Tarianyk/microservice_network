package com.epam.converters;

import com.epam.domain.Profile;
import com.epam.dto.ProfileDto;
import org.springframework.core.convert.converter.Converter;

public class ProfileDtoToProfileConverter implements Converter<ProfileDto, Profile> {

    @Override
    public Profile convert(ProfileDto profileDto) {
        Profile profile = new Profile();
        profile.setName(profileDto.getName());
        profile.setUsername(profileDto.getUsername());
        profile.setDateOfBirth(profileDto.getDateOfBirth());

        return profile;
    }
}
