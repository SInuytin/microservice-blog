package main.friends.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import main.common.CurrentUser;
import main.friends.exceptions.FriendshipDontExistException;
import main.friends.exceptions.WrongFriendshipStatusException;
import main.friends.model.Friendship;
import main.friends.model.FriendshipStatus;
import main.friends.repository.FriendRepository;
import main.users.dto.UserResponse;
import main.users.service.UserService;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

    private final FriendRepository repository;
    private final CurrentUser currentUser;
    private final UserService userService;
    @Override
    public void sendFriendRequest(Long friendId) {
        UserResponse friend = userService.getUserById(friendId);
        Friendship friendship = new Friendship();
        friendship.setUserId(currentUser.getId());
        friendship.setFriendId(friend.id());
        friendship.setStatus(FriendshipStatus.PENDING);
        repository.save(friendship);
    }

    @Override
    public void unsendFriendRequest(Long friendId) {
        Long id = currentUser.getId();
        UserResponse friend = userService.getUserById(friendId);
        Optional<Friendship> friendship = repository.findByUserIdAndFriendId(id, friend.id());
        if (!friendship.isPresent()) {
            throw new FriendshipDontExistException(id, friendId);
        }
        if (friendship.get().getStatus() != FriendshipStatus.PENDING) {
            throw new WrongFriendshipStatusException(friendship.get().getStatus());
        }
        repository.deleteByUserIdAndFriendId(id, friendId);
    }

    @Override
    public void acceptFriendRequest(Long friendId) {
        UserResponse friend = userService.getUserById(friendId);
        Friendship friendship = repository.findByUserIdAndFriendId(friend.id(), currentUser.getId())
          .orElseThrow(() -> new FriendshipDontExistException(currentUser.getId(), friendId));
        friendship.setStatus(FriendshipStatus.ACCEPTED);
    }

    @Override
    public void rejectFriendRequest(Long friendId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rejectFriendRequest'");
    }

    @Override
    public void deleteFriend(Long friendId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFriend'");
    }

    @Override
    public List<Long> getAllFriends() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllFriends'");
    }

    @Override
    public List<Long> getIncomingRequests() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIncomingRequests'");
    }

    @Override
    public List<Long> getOutcomingRequests() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutcomingRequests'");
    }

}
