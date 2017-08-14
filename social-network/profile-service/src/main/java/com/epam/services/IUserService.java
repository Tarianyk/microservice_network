package com.epam.services;

import com.epam.domain.User;
import com.epam.dto.UserDto;

public interface IUserService {
    User registerUser(UserDto userDto);

    User getUserByName(String username);

    User loginUser(UserDto userDto);
}
