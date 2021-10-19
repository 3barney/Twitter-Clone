package com.barney.twitter_clone_rest_api.model;

import com.barney.twitter_clone_rest_api.model.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tweet")
public class Tweet extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 281)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private User tweetUser;

    private Long retweets = 0L;
}
