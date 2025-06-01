package com.twitter.twitter.project.service;

import com.twitter.twitter.project.exception.TwitException;
import com.twitter.twitter.project.exception.UserException;
import com.twitter.twitter.project.model.Like;
import com.twitter.twitter.project.model.User;

import java.util.List;

public interface LikeService {
    public Like likeTwit(Long twitId, User user) throws UserException, TwitException;
    public List<Like> getAllLikes(Long twitId) throws TwitException;
}
