package com.barney.twitter_clone_rest_api.service;

import com.barney.twitter_clone_rest_api.dto.request.TweetRequest;
import com.barney.twitter_clone_rest_api.dto.response.TweetResponse;
import com.barney.twitter_clone_rest_api.exception.ApplicationFailureException;
import com.barney.twitter_clone_rest_api.exception.ResourceNotFoundException;
import com.barney.twitter_clone_rest_api.model.Tweet;
import com.barney.twitter_clone_rest_api.repository.TweetRepository;
import com.barney.twitter_clone_rest_api.repository.UserRepository;
import com.barney.twitter_clone_rest_api.security.UserPrincipal;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TweetResponse> getAllTweets(UserPrincipal userPrincipal) {
        return tweetRepository.findAll().stream()
                .map(tweet -> {
                    return new TweetResponse(
                            tweet.getId(),
                            tweet.getContent(),
                            tweet.getTweetUser().getId(),
                            tweet.getTweetUser().getUsername()
                    );
                })
                .collect(Collectors.toList());
    }


    public Tweet createTweet(UserPrincipal userPrincipal, TweetRequest tweetRequest) {
        try {
            val userOptional = userRepository.findByUsername(userPrincipal.getUsername());

            if (userOptional.isPresent()) {
                val tweetItem = new Tweet();
                tweetItem.setContent(tweetRequest.getContent());
                tweetItem.setTweetUser(userOptional.get());

                return tweetRepository.save(tweetItem);
            } else {
                throw new ResourceNotFoundException("User", "user_name", userPrincipal.getName());
            }
        } catch (Exception ex) {
            throw new ApplicationFailureException("Error occured during tweet creation");
        }
    }

    public List<TweetResponse> getUsersTweets(String userName) {
        return tweetRepository
                .findByTweetUser_usernameOrContentContains(userName, String.format("@%s", userName))
                .stream()
                .map(tweet -> {
                    return new TweetResponse(
                            tweet.getId(),
                            tweet.getContent(),
                            tweet.getTweetUser().getId(),
                            tweet.getTweetUser().getUsername()
                    );
                })
                .collect(Collectors.toList());
    }

    public Boolean retweet(Long tweetIdentifier) {
        try {
            val tweet = tweetRepository.findById(tweetIdentifier);

            if (tweet.isPresent()) {
                val currentTweet = tweet.get();
                currentTweet.setRetweets(currentTweet.getRetweets() + 1);
                tweetRepository.save(currentTweet);
                return true;
            } else {
                throw new ResourceNotFoundException("Tweet", "tweet_id", String.valueOf(tweetIdentifier));
            }
        } catch (Exception ex) {
            throw new ApplicationFailureException("Error occured during retweet");
        }
    }

    public List<TweetResponse> search(String search) {
        return tweetRepository.findByContentIsContainingIgnoreCase(search).stream().map(tweet -> {
                    return new TweetResponse(
                            tweet.getId(),
                            tweet.getContent(),
                            tweet.getTweetUser().getId(),
                            tweet.getTweetUser().getUsername()
                    );
                })
                .collect(Collectors.toList());
    }
}
