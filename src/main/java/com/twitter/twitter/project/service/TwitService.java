package com.twitter.twitter.project.service;

import com.twitter.twitter.project.exception.TwitException;
import com.twitter.twitter.project.exception.UserException;
import com.twitter.twitter.project.model.Twit;
import com.twitter.twitter.project.model.User;
import com.twitter.twitter.project.request.TwitReplyReques;

import java.util.List;

public interface TwitService {

    public Twit createTwit(Twit req, User user) throws UserException;
    public List<Twit> findAllTwit();
    public Twit retwit(Long twitId, User user) throws UserException, TwitException;
    public Twit findById(Long twitId) throws TwitException;
    public void deleteTwitById(Long twitId, Long userId) throws TwitException, UserException;
    public Twit removeFromRetwit(Long twitId, User user) throws TwitException, UserException;
    public Twit createdReply(TwitReplyReques req, User user) throws TwitException;
    public List<Twit> getUserTwit(User user);
    public List<Twit> findByLikesContainsUser(User user);

}
