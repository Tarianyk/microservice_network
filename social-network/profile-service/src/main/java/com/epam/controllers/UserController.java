package com.epam.controllers;

import com.epam.domain.Friend;
import com.epam.domain.Profile;
import com.epam.domain.TimeLine;
import com.epam.domain.User;
import com.epam.dto.FriendDto;
import com.epam.dto.ProfileDto;
import com.epam.dto.TimeLineDto;
import com.epam.dto.UserDto;
import com.epam.services.IFriendService;
import com.epam.services.IProfileService;
import com.epam.services.ITimeLineService;
import com.epam.services.IUserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    @Autowired
    private IProfileService profileService;
    @Autowired
    private ITimeLineService timeLineService;
    @Autowired
    private IFriendService friendService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Profile> createProfile(@RequestBody @Valid ProfileDto profileDto) {
        return new ResponseEntity<Profile>(profileService.createProfile(profileDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{name}/timelines", method = RequestMethod.POST)
    public ResponseEntity<TimeLine> addNoteToTimeLine(@PathVariable(value = "name") @NotEmpty String name, @RequestBody @Valid TimeLineDto timeLineDto) {
        return new ResponseEntity<TimeLine>(timeLineService.addNoteToTimeLine(name, timeLineDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{name}/timelines", method = RequestMethod.GET)
    public ResponseEntity<List<TimeLine>> getAllTimeLinesByName(@PathVariable(value = "name") @NotEmpty String name) {
        return new ResponseEntity<List<TimeLine>>(timeLineService.getAllTimeLinesByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/friends", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addFriend(@PathVariable(value = "name") @NotEmpty String name, @RequestBody @Valid FriendDto friendDto) {
        return new ResponseEntity<Boolean>(friendService.addFriend(name, friendDto), HttpStatus.CREATED);
    }

    //TODO; remake relationship
    @RequestMapping(value = "/{name}/friends", method = RequestMethod.GET)
    public ResponseEntity<List<Friend>> getListOfFriends(@PathVariable(value = "name") @NotEmpty String name) {
        return new ResponseEntity<List<Friend>>(friendService.getListOfFriends(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/friends/{nameOfFriend}/timelines", method = RequestMethod.POST)
    public ResponseEntity<TimeLine> addTimeLineToFriend(@PathVariable(value = "name") @NotEmpty String name,
                                                        @PathVariable(value = "nameOfFriend") @NotEmpty String nameOfFriend,
                                                        @RequestBody @Valid TimeLineDto timeLineDto) {
        return new ResponseEntity<TimeLine>(timeLineService.addTimeLine(name, nameOfFriend, timeLineDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{name}/friends/{nameOfFriend}/timelines", method = RequestMethod.GET)
    public ResponseEntity<List<TimeLine>> getTimeLinesOfFriends(@PathVariable(value = "name") @NotEmpty String name,
                                                                @PathVariable(value = "nameOfFriend") @NotEmpty String nameOfFriend) {
        return new ResponseEntity<List<TimeLine>>(timeLineService.getAllTimeLinesByName(nameOfFriend), HttpStatus.OK);
    }

    //
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<User>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    @Autowired
    private ConversionService conversionService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> loginUser(@RequestBody @Valid @AuthenticationPrincipal UserDto userDto,
                                            HttpSession session) {
        System.out.println("userDto " + userDto);
        if (session.getAttribute("user") == null) {
            User user = conversionService.convert(userDto, User.class);
            session.setAttribute("user", user);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }

        return new ResponseEntity<String>("already", HttpStatus.OK);
    }












    @RequestMapping(value = "/loginsuccess", method = RequestMethod.GET)
    public ResponseEntity<String> loginsuccess() {
        return new ResponseEntity<String>("loginsuccess", HttpStatus.OK);
    }

    @RequestMapping(value = "/loginerror", method = RequestMethod.GET)
    public ResponseEntity<String> loginerror() {
        return new ResponseEntity<String>("loginerror", HttpStatus.OK);
    }

}