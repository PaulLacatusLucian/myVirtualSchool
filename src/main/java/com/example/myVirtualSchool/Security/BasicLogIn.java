package com.example.myVirtualSchool.Security;

import com.example.myVirtualSchool.Domain.User;
import com.example.myVirtualSchool.Service.Impl.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicLogIn {
    private final UserService userService;

    public BasicLogIn(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/basic_login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            return "redirect:/homepage";
        } else {
            return "redirect:/registration";
        }
    }

    @PostMapping("/basic_signup")
    public String signup(@RequestBody SignupRequest signupRequest) {
        String username = signupRequest.getUsername();
        String password = signupRequest.getPassword();

        if (userService.isUsernameTaken(username)) {
            return "redirect:/login?error=username_taken";
        }

        User newUser = new User(username, password);
        userService.create(newUser);

        return "redirect:/registration";
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

    }

    public static class SignupRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

    }
}
