package com.twitter.twitter.project.repository;

import com.twitter.twitter.project.model.Twit;
import com.twitter.twitter.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TwitRepository extends JpaRepository<Twit, Long> {
    List<Twit> findAllByIsTwitTrueOrderByCreatedAtDesc();
    List<Twit> findByRetwitUserContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(User user, Long userId);
    List<Twit> findByLikesContainingOrderByCreatedAtDesc(User user);
    @Query("Select t FROM Twit t JOIN t.likes l where l.user.id=:userId")
    List<Twit> findByLikesUser_id(Long userId);


}
