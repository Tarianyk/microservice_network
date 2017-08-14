package com.epam.services;

import com.epam.domain.Friend;
import com.epam.domain.TimeLine;
import com.epam.dto.FriendDto;
import com.epam.dto.TimeLineDto;

import java.util.List;

public interface IFriendService {
    boolean addFriend(String name, FriendDto friendDto);

    List<Friend> getListOfFriends(String name);
}
