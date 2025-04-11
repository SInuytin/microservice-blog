package main.friends.service;

import java.util.List;

import main.friends.model.FriendRequest;

public interface FriendsService {
    public void sendFriendRequest(Long userId, Long subId);
    public void unsendFriendRequest(Long userId, Long subId);
    
    public void acceptFriendRequest(Long userId, Long friendRequestId);
    public void rejectFriendRequest(Long userId, Long friendRequestId);

    public void deleteFriend(Long userId, Long friendId);

    public List<FriendResponse> getAllFriends(Long userId);
    public List<FriendRequestResponse> getIncomingRequests(Long userId);
    public List<FriendRequestResponse> getOutcomingRequests(Long userId);
}
