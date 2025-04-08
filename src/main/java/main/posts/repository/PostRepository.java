package main.posts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.posts.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    public List<Post> findAllByTitle(String title);
};
