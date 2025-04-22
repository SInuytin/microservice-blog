package main.friends.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import main.friends.dto.FriendshipRequest;
import main.friends.dto.FriendshipResponse;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    @Override
    public void sendFriendRequest(FriendshipRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendFriendRequest'");
    }

    @Override
    public void unsendFriendRequest(Long friendshipId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unsendFriendRequest'");
    }

    @Override
    public void acceptFriendRequest(FriendshipRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptFriendRequest'");
    }

    @Override
    public void rejectFriendRequest(Long userId, Long friendId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rejectFriendRequest'");
    }

    @Override
    public void deleteFriend(Long userId, Long friendId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFriend'");
    }

    @Override
    public List<FriendshipResponse> getAllFriends(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllFriends'");
    }

    @Override
    public List<FriendshipRequest> getIncomingRequests(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIncomingRequests'");
    }

    @Override
    public List<FriendshipRequest> getOutcomingRequests(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutcomingRequests'");
    }

}
