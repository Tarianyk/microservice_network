package com.epam.converters;

import com.epam.domain.User;
import com.epam.dto.UserDto;
import org.springframework.core.convert.converter.Converter;

public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());

        return user;
    }
}
