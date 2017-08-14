package com.epam.services.impl;

import com.epam.domain.Role;
import com.epam.domain.User;
import com.epam.dto.UserDto;
import com.epam.exceptions.UserNotFoundException;
import com.epam.repository.UserRepository;
import com.epam.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("userDetailsService")
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConversionService conversionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("--> " + username);
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                getRoles(user));
    }

    private Set<GrantedAuthority> getRoles(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public User registerUser(UserDto userDto) {
        Optional<User> userByName = Optional.ofNullable(getUserByName(userDto.getUsername()));
        if (userByName.isPresent()) {
            throw new UserNotFoundException("The user with the same name exists!", HttpStatus.BAD_REQUEST);
        }

        User user = conversionService.convert(userDto, User.class);
        addDefaultRole(user);

        return userRepository.save(user);
    }

    private void addDefaultRole(User user) {
        Role role = new Role();
        role.setName("USER");
        user.setRoles(Collections.singletonList(role));
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User loginUser(UserDto userDto) {
        return null;
    }
}
