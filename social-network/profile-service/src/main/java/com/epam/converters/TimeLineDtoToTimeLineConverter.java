package com.epam.converters;

import com.epam.domain.TimeLine;
import com.epam.dto.TimeLineDto;
import org.springframework.core.convert.converter.Converter;

public class TimeLineDtoToTimeLineConverter implements Converter<TimeLineDto, TimeLine> {

    @Override
    public TimeLine convert(TimeLineDto timeLineDto) {
        TimeLine timeLine = new TimeLine();
        timeLine.setNoteText(timeLineDto.getNoteText());

        return timeLine;
    }
}
