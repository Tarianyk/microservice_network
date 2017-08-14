package com.epam.services.impl;

import com.epam.domain.Friend;
import com.epam.domain.Profile;
import com.epam.dto.FriendDto;
import com.epam.exceptions.ProfileNotFoundException;
import com.epam.services.IFriendService;
import com.epam.services.IProfileService;
import com.epam.services.ITimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FriendService implements IFriendService {

    @Autowired
    private IProfileService profileService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private ITimeLineService timeLineService;

    @Override
    public boolean addFriend(String name, FriendDto friendDto) {
        Optional<Profile> userByName = Optional.ofNullable(profileService.getUserByName(name));
        checkIfUserExists(userByName);

        Profile profile = userByName.get();
        profile.setFriends(addFriendToSet(friendDto));

        return true;
    }

    private void checkIfUserExists(Optional<Profile> userByName) {
        if (!userByName.isPresent()) {
            throw new ProfileNotFoundException("The user with specified name doesnt exist.", HttpStatus.BAD_GATEWAY);
        }
    }

    @Override
    public List<Friend> getListOfFriends(String name) {
        Optional<Profile> userByName = Optional.ofNullable(profileService.getUserByName(name));
        checkIfUserExists(userByName);

        System.err.println(userByName.get() + "  " + userByName.get().getFriends().size());

        return new ArrayList<>(userByName.get().getFriends());
    }

    private Set<Friend> addFriendToSet(FriendDto friendDto) {
        return new HashSet<>(Collections.singletonList(conversionService.convert(friendDto, Friend.class)));
    }
}
