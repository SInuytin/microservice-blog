package main.friends.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.common.CurrentUser;
import main.friends.exceptions.FriendshipAlreadyExistsException;
import main.friends.exceptions.FriendshipDontExistException;
import main.friends.exceptions.WrongFriendshipStatusException;
import main.friends.model.Friendship;
import main.friends.model.FriendshipStatus;
import main.friends.repository.FriendRepository;
import main.users.dto.UserResponse;
import main.users.service.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository repository;
    private final CurrentUser currentUser;
    private final UserService userService;

    private Friendship getFriendshipById(Long sender, Long replier, FriendshipStatus checkStatus) {
        userService.getUserById(replier);
        Friendship friendship = repository.findByUserIdAndFriendId(sender, replier)
                .orElseThrow(() -> new FriendshipDontExistException(sender, replier));
        if (friendship.getStatus() != checkStatus) {
            throw new WrongFriendshipStatusException(friendship.getStatus());
        }
        return friendship;
    }

    private Friendship createFriendship(Long sender, Long replier, FriendshipStatus status) {
        if (repository.findByUserIdAndFriendId(sender, replier).isPresent())
            throw new FriendshipAlreadyExistsException(sender, replier);
        Friendship friendship = new Friendship();
        friendship.setUserId(sender);
        friendship.setFriendId(replier);
        friendship.setStatus(status);
        return friendship;
    }

    @Override
    public void sendFriendRequest(Long friendId) {
        userService.getUserById(friendId);
        repository.save(
            createFriendship(currentUser.getId(), friendId, FriendshipStatus.PENDING)
        );
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
        Friendship req = getFriendshipById(
            friendId, currentUser.getId(), FriendshipStatus.PENDING
        );
        req.setStatus(FriendshipStatus.ACCEPTED);
        repository.save(req);
        Friendship back = createFriendship(currentUser.getId(), friendId, FriendshipStatus.ACCEPTED);
        repository.save(back);
    }

    @Override
    public void rejectFriendRequest(Long friendId) {
        Friendship friendship = getFriendshipById(friendId, currentUser.getId(), FriendshipStatus.PENDING);
        repository.delete(friendship);
    }

    @Override
    public void deleteFriend(Long friendId) {
        Friendship req = getFriendshipById(currentUser.getId(), friendId, FriendshipStatus.ACCEPTED);
        Friendship back = getFriendshipById(friendId, currentUser.getId(),FriendshipStatus.ACCEPTED);
        repository.delete(req);
        repository.delete(back);
    }

    @Override
    public List<Long> getAllFriends() {
        return repository
            .findAllByUserIdAndStatus(
                currentUser.getId(), FriendshipStatus.ACCEPTED
            ).stream().map(Friendship::getFriendId).toList();
    }

    @Override
    public List<Long> getIncomingRequests() {
        return repository
            .findAllByFriendIdAndStatus(
                currentUser.getId(), FriendshipStatus.PENDING
            ).stream().map(Friendship::getFriendId).toList();
    }

    @Override
    public List<Long> getOutcomingRequests() {
        return repository
            .findAllByUserIdAndStatus(
                currentUser.getId(), FriendshipStatus.PENDING
            ).stream().map(Friendship::getFriendId).toList();
    }

}
