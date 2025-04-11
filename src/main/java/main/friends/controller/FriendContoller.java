package main.friends.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import main.friends.service.FriendsService;

@RestController
@RequestMapping("users/{userId}/friends")
@RequiredArgsConstructor
public class FriendContoller {
    private final FriendsService friendsService;

    @PostMapping("/requests")
    public ResponseEntity<Void> sendFriendRequest(
        @PathVariable Long userId,
        @RequestParam Long toUserId
    ) {
        friendsService.sendFriendRequest(userId, toUserId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/requests/{fromUserId}/accept")
    public ResponseEntity<Void> acceptFriendRequest(
        @PathVariable Long userId,
        @PathVariable Long fromUserId
    ) {
        friendsService.acceptFriendRequest(userId, fromUserId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/requests/{fromUserId}/reject")
    public ResponseEntity<Void> rejectFriendRequest(@PathVariable Long userId,
                                                    @PathVariable Long fromUserId) {
        friendsService.rejectFriendRequest(userId, fromUserId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FriendResponse>> getFriends(@PathVariable Long userId) {
        return ResponseEntity.ok(friendsService.getAllFriends(userId));
    }
}
