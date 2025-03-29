package main.posts.service;


import java.util.List;
import java.util.Optional;

import main.posts.dto.PostCreateRequest;
import main.posts.dto.PostEditRequest;
import main.posts.dto.PostResponse;

public interface PostService {
    
    PostResponse create(PostCreateRequest post);
    boolean delete(Long id);

    List<PostResponse> getAllPosts();
    Optional<PostResponse> findById(Long id);
    List<PostResponse> searchByTitle(String title);

    Optional<PostResponse> editPost(Long id, PostEditRequest request);
}
