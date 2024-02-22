package com.example.myVirtualSchool.Security;

import com.example.myVirtualSchool.Domain.User;
import com.example.myVirtualSchool.Service.Impl.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class BasicLogIn {
    private final UserService userService;
    public BasicLogIn(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/basic_login")
    @PermitAll
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            return "redirect:/homepage";
        } else {
            return "redirect:/basic_login";
        }
    }

    @PostMapping("basic_signup")
    @PermitAll
    public String signup(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        if (userService.isUsernameTaken(username)) {
            return "redirect:/login?error=username_taken";
        }

        User newUser = new User(username, password);
        userService.create(newUser);

        return "redirect:/basic_login";
    }
}
