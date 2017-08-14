package com.epam.services.impl;

import com.epam.domain.Profile;
import com.epam.domain.TimeLine;
import com.epam.dto.TimeLineDto;
import com.epam.exceptions.ProfileNotFoundException;
import com.epam.repository.TimeLineRepository;
import com.epam.services.IProfileService;
import com.epam.services.ITimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TimeLineService implements ITimeLineService {

    @Autowired
    private TimeLineRepository timeLineRepository;
    @Autowired
    private IProfileService profileService;
    @Autowired
    private ConversionService conversionService;

    @Override
    public TimeLine addNoteToTimeLine(String name, TimeLineDto timeLineDto) {
        Optional<Profile> userByName = Optional.ofNullable(profileService.getUserByName(name));
        checkIfUserExists(userByName);

        TimeLine timeLine = conversionService.convert(timeLineDto, TimeLine.class);
        timeLine.setProfile(userByName.get());

        return timeLineRepository.save(timeLine);
    }

    @Override
    public List<TimeLine> getAllTimeLinesByName(String name) {
        Optional<Profile> userByName = Optional.ofNullable(profileService.getUserByName(name));
        checkIfUserExists(userByName);
        Set<TimeLine> timeLines = profileService.getUserByName(userByName.get().getName()).getTimeLines();

        return new ArrayList<>(timeLines);

    }

    private void checkIfUserExists(Optional<Profile> userByName) {
        if (!userByName.isPresent()) {
            throw new ProfileNotFoundException("The user with specified name doesnt exist.", HttpStatus.BAD_GATEWAY);
        }
    }

    @Override
    public TimeLine addTimeLine(String name, String nameOfFriend, TimeLineDto timeLineDto) {
        return addNoteToTimeLine(nameOfFriend, timeLineDto);
    }
}
