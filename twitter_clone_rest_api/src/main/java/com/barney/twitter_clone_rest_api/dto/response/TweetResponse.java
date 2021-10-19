package com.barney.twitter_clone_rest_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetResponse {

    private Long id;
    private String content;
    private Long userId;
    private String userName;
}
