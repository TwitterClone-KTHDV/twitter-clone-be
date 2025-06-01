package com.twitter.twitter.project.controller;

import com.twitter.twitter.project.dto.TwitDto;
import com.twitter.twitter.project.exception.TwitException;
import com.twitter.twitter.project.exception.UserException;
import com.twitter.twitter.project.mapper.TwitDtoMapper;
import com.twitter.twitter.project.model.Twit;
import com.twitter.twitter.project.model.User;
import com.twitter.twitter.project.request.TwitReplyReques;
import com.twitter.twitter.project.service.TwitService;
import com.twitter.twitter.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/twits")
public class TwitController {
    @Autowired
    private TwitService twitService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TwitDto> createTwit(@RequestBody Twit req, @RequestHeader("Authorization") String jwt)
            throws UserException, TwitException{
        User user = userService.findUserProfileByJwt(jwt);
        Twit twit = twitService.createTwit(req, user);
        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit, user);
        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);
    }

    @PostMapping("/reply")
    public ResponseEntity<TwitDto> replyTwit(@RequestBody TwitReplyReques req, @RequestHeader("Authorization") String jwt)
            throws UserException, TwitException{
        User user = userService.findUserProfileByJwt(jwt);
        Twit twit = twitService.createdReply(req, user);
        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit, user);
        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);
    }

    @PutMapping("/{twitId}/retwit")
    public ResponseEntity<TwitDto> retwit(@PathVariable Long twitId, @RequestHeader("Authorization") String jwt)
            throws UserException, TwitException{
        User user = userService.findUserProfileByJwt(jwt);
        Twit twit = twitService.retwit(twitId, user);
        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit, user);
        return new ResponseEntity<>(twitDto, HttpStatus.OK);
    }

    @GetMapping("/{twitId}")
    public ResponseEntity<TwitDto> findTwitById(@PathVariable Long twitId, @RequestHeader("Authorization") String jwt)
            throws UserException, TwitException{
        User user = userService.findUserProfileByJwt(jwt);
        Twit twit = twitService.findById(twitId);
        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit, user);
        return new ResponseEntity<>(twitDto, HttpStatus.OK);
    }

    @DeleteMapping("/{twitId}")
    public ResponseEntity<TwitDto> deleteTwit(@PathVariable Long twitId, @RequestHeader("Authorization") String jwt)
            throws UserException, TwitException{
        User user = userService.findUserProfileByJwt(jwt);
        twitService.deleteTwitById(twitId, user.getId());
        ApiRespone res = new ApiRespone();
        return new ResponseEntity<>(twitDto, HttpStatus.OK);
    }
}
