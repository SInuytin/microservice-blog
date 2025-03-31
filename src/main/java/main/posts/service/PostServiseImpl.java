package main.posts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import main.posts.dto.PostCreateRequest;
import main.posts.dto.PostEditRequest;
import main.posts.dto.PostResponse;

@Service
public class PostServiseImpl implements PostService{

    @Override
    public PostResponse create(PostCreateRequest post) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<PostResponse> getAllPosts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPosts'");
    }

    @Override
    public List<PostResponse> searchByTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByTitle'");
    }

    @Override
    public Optional<PostResponse> editPost(Long id, PostEditRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editPost'");
    }

    @Override
    public Optional<PostResponse> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
