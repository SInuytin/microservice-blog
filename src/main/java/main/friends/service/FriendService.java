package main.friends.service;

import java.util.List;


public interface FriendService {
    public void sendFriendRequest(Long friendId);
    public void unsendFriendRequest(Long friendId);
    
    public void acceptFriendRequest(Long friendId);
    public void rejectFriendRequest(Long friendId);


    public List<Long> getAllFriends();
    public void deleteFriend(Long friendId);

    public List<Long> getIncomingRequests();
    public List<Long> getOutcomingRequests();
}
