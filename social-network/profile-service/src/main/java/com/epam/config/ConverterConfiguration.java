package com.epam.config;

import com.epam.converters.FriendDtoToFriendConverter;
import com.epam.converters.ProfileDtoToProfileConverter;
import com.epam.converters.TimeLineDtoToTimeLineConverter;
import com.epam.converters.UserDtoToUserConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConverterConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ProfileDtoToProfileConverter());
        registry.addConverter(new TimeLineDtoToTimeLineConverter());
        registry.addConverter(new FriendDtoToFriendConverter());
        registry.addConverter(new UserDtoToUserConverter());
    }
}
