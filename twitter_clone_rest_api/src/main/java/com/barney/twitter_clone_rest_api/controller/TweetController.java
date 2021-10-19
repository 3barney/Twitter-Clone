package com.barney.twitter_clone_rest_api.controller;

import com.barney.twitter_clone_rest_api.dto.request.TweetRequest;
import com.barney.twitter_clone_rest_api.dto.response.ApiResponse;
import com.barney.twitter_clone_rest_api.dto.response.TweetResponse;
import com.barney.twitter_clone_rest_api.exception.ApplicationFailureException;
import com.barney.twitter_clone_rest_api.model.Tweet;
import com.barney.twitter_clone_rest_api.security.CurrentUser;
import com.barney.twitter_clone_rest_api.security.UserPrincipal;
import com.barney.twitter_clone_rest_api.service.TweetService;
import io.swagger.annotations.Api;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "authorization")
@RequestMapping("/api/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping
    public List<TweetResponse> getTweets(@CurrentUser UserPrincipal currentUser) {
        return tweetService.getAllTweets(currentUser);
    }

    @PostMapping
    public ResponseEntity<?> createTweet(
            @CurrentUser UserPrincipal currentUser,
            @Valid @RequestBody TweetRequest tweetRequest)
    {
        try {
            tweetService.createTweet(currentUser, tweetRequest);
            return ResponseEntity.ok().body(new ApiResponse(true, "Tweet Created Successfully"));
        } catch (ApplicationFailureException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUsersTweets(@CurrentUser UserPrincipal currentUser) {
        try {
            val tweets = tweetService.getUsersTweets(currentUser.getUsername());
            return ResponseEntity.ok().body(tweets);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping("/{tweetId}/retweet")
    public ResponseEntity<?> retweet(@CurrentUser UserPrincipal currentUser, @PathVariable String tweetId) {
        val tweetIdentifier = Long.valueOf(tweetId);
        val response = tweetService.retweet(tweetIdentifier);
        return ResponseEntity.ok().body(new ApiResponse(response, "retweet"));
    }

    @GetMapping("/{search}")
    public List<TweetResponse> getTweets(@CurrentUser UserPrincipal currentUser, @PathVariable String search) {
        if (search.isEmpty()) {
            return tweetService.getAllTweets(currentUser);
        }
        return tweetService.search(search);
    }
}
