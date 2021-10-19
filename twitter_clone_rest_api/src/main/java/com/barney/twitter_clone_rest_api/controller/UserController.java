package com.barney.twitter_clone_rest_api.controller;

import com.barney.twitter_clone_rest_api.dto.response.ApiResponse;
import com.barney.twitter_clone_rest_api.dto.response.FollowerResponse;
import com.barney.twitter_clone_rest_api.dto.response.UserProfileTweets;
import com.barney.twitter_clone_rest_api.exception.ResourceNotFoundException;
import com.barney.twitter_clone_rest_api.model.User;
import com.barney.twitter_clone_rest_api.repository.UserRepository;
import com.barney.twitter_clone_rest_api.security.CurrentUser;
import com.barney.twitter_clone_rest_api.security.UserPrincipal;
import com.barney.twitter_clone_rest_api.service.TweetService;
import io.swagger.annotations.Api;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "user")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetService tweetService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String userName) {

        val user = userRepository.findByUsername(userName);

        if (user.isPresent()) {
            val tweets = tweetService.getUsersTweets(userName);
            val tweetResponse = new UserProfileTweets(
                    user.get(),
                    tweets
            );
            return ResponseEntity.ok().body(tweetResponse);
        } else {
            throw new ResourceNotFoundException("User", "UserName", userName);
        }
    }

    @PutMapping("/{userId}/follow")
    public ResponseEntity<?> followUser(@CurrentUser UserPrincipal currentUser, @PathVariable String userId) {
        val user = userRepository.findByUsername(currentUser.getUsername());

        if (user.isPresent()) {
            val userItem = user.get();
            // User cant follow themselves
            if (!user.get().getId().equals(userId)) {
                user.get().getFollowing().add(userId);
                userRepository.save(user.get());
            }
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "followed"));
    }

    // TODO: Add new user following
    @GetMapping("/{userName}/followers")
    public ResponseEntity<?> getUserFollwers(@PathVariable String userName) {

        val user = userRepository.findByUsername(userName);
        if (user.isPresent()) {
            val tweets = tweetService.getUsersTweets(userName);
            val tweetResponse = new UserProfileTweets(
                    user.get(),
                    tweets
            );
            return ResponseEntity.ok().body(tweetResponse);
        } else {
            throw new ResourceNotFoundException("User", "UserName", userName);
        }
    }
}