package com.barney.twitter_clone_rest_api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TweetRequest {

    @NotBlank
    @Size(max = 281)
    private String content;
}
