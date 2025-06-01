package com.twitter.twitter.project.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TwitReplyReques {
    private String content;
    private Long twitId;
    private LocalDateTime createdAt;
    private String image;
}
