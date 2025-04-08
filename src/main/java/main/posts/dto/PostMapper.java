package main.posts.dto;

import org.springframework.stereotype.Component;

import main.posts.model.Post;

@Component
public class PostMapper {

    public PostResponse toResponse(Post post) {
        return new PostResponse(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getAuthorId()
        );
    }
}
