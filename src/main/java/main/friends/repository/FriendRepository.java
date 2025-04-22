package main.friends.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import jakarta.transaction.Transactional;
import main.friends.model.Friendship;
import main.friends.model.FriendshipStatus;

public interface FriendRepository extends JpaRepository<Friendship, Long>{
    
    Optional<Friendship> findByUserIdAndFriendId(Long userId, Long friendId);

    List<Friendship> findAllByUserIdAndStatus(Long userId, FriendshipStatus status);

    List<Friendship> findAllByFriendIdAndStatus(Long friendId, FriendshipStatus status);

    @Modifying
    @Transactional
    void deleteByUserIdAndFriendId(Long userId, Long friendId);
}
