package com.barney.twitter_clone_rest_api.repository;

import com.barney.twitter_clone_rest_api.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByTweetUser_usernameOrContentContains(
            String screenName,
            String mention
    );

    List<Tweet> findByContentIsContainingIgnoreCase(String searchQuery);

}