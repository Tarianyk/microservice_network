package com.epam.converters;

import com.epam.domain.Friend;
import com.epam.dto.FriendDto;
import org.springframework.core.convert.converter.Converter;

public class FriendDtoToFriendConverter implements Converter<FriendDto, Friend> {

    @Override
    public Friend convert(FriendDto friendDto) {
        Friend friend = new Friend();
        friend.setUsername(friendDto.getUsername());
        friend.setName(friendDto.getName());
        friend.setDateOfBirth(friendDto.getDateOfBirth());

        return friend;
    }
}
