package main.friends.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.friends.model.Friendship;

public interface FriendRepository extends JpaRepository<Friendship, Long>{
    
}
