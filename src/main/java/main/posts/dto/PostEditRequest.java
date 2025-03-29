package main.posts.dto;

import lombok.Data;

@Data
public class PostEditRequest {
    private String title;
    private String content;
}
