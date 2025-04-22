package main.friends.exceptions;

public class FriendshipAlreadyExistsException extends RuntimeException{
    public FriendshipAlreadyExistsException(Long sender, Long replier) {
        super("Friendship between: " + sender + " and " + replier + "already exists");
    }
}
