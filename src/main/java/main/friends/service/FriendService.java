package main.friends.service;

import java.util.List;

import main.friends.dto.FriendshipRequest;
import main.friends.dto.FriendshipResponse;

public interface FriendService {
    public void sendFriendRequest(FriendshipRequest request);
    public void unsendFriendRequest(Long friendshipId);
    
    public void acceptFriendRequest(FriendshipRequest request);
    public void rejectFriendRequest(Long userId, Long friendId);

    public void deleteFriend(Long userId, Long friendId);

    public List<FriendshipResponse> getAllFriends(Long userId);
    public List<FriendshipRequest> getIncomingRequests(Long userId);
    public List<FriendshipRequest> getOutcomingRequests(Long userId);
}
