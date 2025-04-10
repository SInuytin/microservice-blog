package main.friends.service;

import java.util.List;

import main.friends.model.FriendRequest;

public interface FriendsService {
    public void sendFriendRequest(Long userId, Long subId);
    public void unsendFriendRequest(Long userId, Long subId);
    
    public void rejectFriendRequest(Long friendRequestId);
    public FriendResponse acceptFriendRequest(Long friendRequestId);

    public void deleteFriend(Long friendId);

    public List<FriendResponse> getAllFriends(Long userId);
    public List<FriendRequestResponse> getAllFriendsRequests(Long userId);
}
