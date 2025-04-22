package main.posts.dto;

public record PostResponse (
    Long id,
    String title,
    String content,
    Long authorid
) {};
