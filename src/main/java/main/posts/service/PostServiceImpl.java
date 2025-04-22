package main.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import main.posts.dto.PostCreateRequest;
import main.posts.dto.PostEditRequest;
import main.posts.dto.PostMapper;
import main.posts.dto.PostResponse;
import main.posts.exceptions.TitleDoesNotExistException;
import main.posts.model.Post;
import main.posts.repository.PostRepository;
import main.users.service.UserService;
import main.posts.exceptions.PostNotFoundException;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{
    private PostRepository repository;
    private PostMapper mapper;

    @Override
    @Transactional
    public PostResponse create(Long authorId, PostCreateRequest createRequest) {
        Post post = new Post();
        post.setAuthorId(authorId);
        post.setTitle(createRequest.title());
        post.setContent(createRequest.content());
        Post saved = repository.save(post);
        
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            throw new PostNotFoundException(id);
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> searchByTitle(String title) {
        List<Post> list = repository.findAllByTitle(title);
        if (list.isEmpty()) 
            throw new TitleDoesNotExistException(title);
        return list.stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostResponse editPost(Long id, PostEditRequest request) {
        Post post = repository.findById(id)
            .orElseThrow(() -> new PostNotFoundException(id));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        repository.save(post);
        return mapper.toResponse(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        Post post = repository.findById(id)
            .orElseThrow(() -> new PostNotFoundException(id));
        return mapper.toResponse(post);
    }

    
}
