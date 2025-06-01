package com.twitter.twitter.project.service;

import com.twitter.twitter.project.exception.TwitException;
import com.twitter.twitter.project.exception.UserException;
import com.twitter.twitter.project.model.Like;
import com.twitter.twitter.project.model.Twit;
import com.twitter.twitter.project.model.User;
import com.twitter.twitter.project.repository.LikeRepository;
import com.twitter.twitter.project.repository.TwitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplement implements LikeService{
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private TwitService twitService;
    @Autowired
    private TwitRepository twitRepository;

    @Override
    public Like likeTwit(Long twitId, User user) throws UserException, TwitException {
        Like isLikeExit = likeRepository.isLikeExist(user.getId(), twitId);
        if(isLikeExit!=null){
            likeRepository.deleteById(isLikeExit.getId());
            return isLikeExit;
        }
        Twit twit = twitService.findById(twitId);
        Like like = new Like();
        like.setTwit(twit);
        like.setUser(user);
        Like savedLike = likeRepository.save(like);
        twit.getLikes().add(savedLike);
        twitRepository.save(twit);
        return savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long twitId) throws TwitException {
        Twit twit = twitService.findById(twitId);
        List<Like> likes = likeRepository.findByTwitId(twitId);

        return likes;
    }
}
