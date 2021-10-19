package com.barney.twitter_clone_rest_api.dto.response;

import com.barney.twitter_clone_rest_api.model.Tweet;
import com.barney.twitter_clone_rest_api.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileTweets {
    private User user;
    private List<TweetResponse> tweetResponses;
}
