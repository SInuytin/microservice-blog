package main.posts.service;


import java.util.List;

import main.posts.dto.PostCreateRequest;
import main.posts.dto.PostEditRequest;
import main.posts.dto.PostResponse;

public interface PostService {
    
    PostResponse create(Long authorId, PostCreateRequest post);
    boolean delete(Long id);

    List<PostResponse> getAllPosts();
    PostResponse findById(Long id);
    List<PostResponse> searchByTitle(String title);

    PostResponse editPost(Long id, PostEditRequest request);
}
