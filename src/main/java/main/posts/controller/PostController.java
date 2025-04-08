package main.posts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.posts.dto.PostCreateRequest;
import main.posts.dto.PostEditRequest;
import main.posts.dto.PostResponse;
import main.posts.service.PostService;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
    @GetMapping("/search") 
    public ResponseEntity<List<PostResponse>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(postService.searchByTitle(title));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostCreateRequest request) {
        PostResponse response = postService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> editPost(
        @PathVariable Long id, @RequestBody @Valid PostEditRequest request
    ) {
        return ResponseEntity.ok(postService.editPost(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean deleted = postService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.notFound().build();
    }
}
