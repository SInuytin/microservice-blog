package main.friends.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import main.friends.service.FriendService;

@RestController
@RequestMapping("api/friends")
@RequiredArgsConstructor
public class FriendContoller {
    private final FriendService friendService;

    @PostMapping("/request/{id}")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable Long id) {
        friendService.sendFriendRequest(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/request/{id}")
    public ResponseEntity<Void> unsendFriendRequest(@PathVariable Long id) {
        friendService.unsendFriendRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/responce/{id}/accept")
    public ResponseEntity<Void> acceptFriendRequest(
        @PathVariable Long id
    ) {
        friendService.acceptFriendRequest(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/responce/{id}/reject")
    public ResponseEntity<Void> rejectFriendRequest(
        @PathVariable Long id
    ) {
        friendService.rejectFriendRequest(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        friendService.deleteFriend(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Long>> getFriends() {
        return ResponseEntity.ok(friendService.getAllFriends());
    }

    @GetMapping("/request")
    public ResponseEntity<List<Long>> getIncomingRequests() {
        return ResponseEntity.ok(friendService.getIncomingRequests());
    }
    @GetMapping("/responce")
    public ResponseEntity<List<Long>> getOutcomingRequests() {
        return ResponseEntity.ok(friendService.getOutcomingRequests());
    }
}
