package com.epam.services;

import com.epam.domain.TimeLine;
import com.epam.dto.TimeLineDto;

import java.util.List;

public interface ITimeLineService {
    TimeLine addNoteToTimeLine(String name, TimeLineDto timeLineDto);

    List<TimeLine> getAllTimeLinesByName(String name);

    TimeLine addTimeLine(String name, String nameOfFriend, TimeLineDto timeLineDto);
}
