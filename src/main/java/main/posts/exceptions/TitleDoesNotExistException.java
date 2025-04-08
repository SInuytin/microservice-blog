package main.posts.exceptions;

public class TitleDoesNotExistException extends RuntimeException{
    public TitleDoesNotExistException(String title) {
        super("Posts with title: "+title+", doesn't exist");
    }
}
