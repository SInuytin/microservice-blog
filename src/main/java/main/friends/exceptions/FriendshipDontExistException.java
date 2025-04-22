package main.friends.exceptions;

public class FriendshipDontExistException extends RuntimeException{
    public FriendshipDontExistException(Long id, Long friendId) {
        super("There's no friendship between: "+id.toString()+", "+friendId.toString());
    }
}
