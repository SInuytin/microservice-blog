package main.posts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostCreateRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Long authorId;
}
