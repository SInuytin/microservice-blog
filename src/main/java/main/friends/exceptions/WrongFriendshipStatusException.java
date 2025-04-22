package main.friends.exceptions;

import main.friends.model.FriendshipStatus;

public class WrongFriendshipStatusException extends RuntimeException{
    public WrongFriendshipStatusException(FriendshipStatus status) {
        super("This friendship is different status than " + status.toString());
    }
}
