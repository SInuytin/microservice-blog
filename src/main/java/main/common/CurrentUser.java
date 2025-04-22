package main.common;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import main.users.model.User;

@Component
public class CurrentUser {
    public Long getId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof User userDetails) {
            return userDetails.getId();
        }
        throw new AccessDeniedException("User not authenticated");
    }
}
