package main.friends.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import main.friends.dto.FriendshipRequest;
import main.friends.dto.FriendshipResponse;
import main.friends.service.FriendService;

@RestController
@RequestMapping("api/friends")
@RequiredArgsConstructor
public class FriendContoller {
    private final FriendService friendService;

    @PostMapping()
    public ResponseEntity<Void> sendFriendRequest(@RequestBody FriendshipRequest request) {
        friendService.sendFriendRequest(request);
        return ResponseEntity.ok().build();
    }    

    @PostMapping("/requests/accept")
    public ResponseEntity<Void> acceptFriendRequest(
        @RequestBody FriendshipRequest request
    ) {
        friendService.acceptFriendRequest(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/requests/{fromUserId}/reject")
    public ResponseEntity<Void> rejectFriendRequest(@PathVariable Long userId,
                                                    @PathVariable Long fromUserId) {
        friendService.rejectFriendRequest(userId, fromUserId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FriendshipResponse>> getFriends(@PathVariable Long userId) {
        return ResponseEntity.ok(friendService.getAllFriends(userId));
    }
}
