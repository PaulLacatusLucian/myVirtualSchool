package com.example.myVirtualSchool.Controller.Impl;

import com.example.myVirtualSchool.Controller.ControllerInterface;
import com.example.myVirtualSchool.Domain.Subject;
import com.example.myVirtualSchool.Domain.User;
import com.example.myVirtualSchool.Helpers.ObjectUpdater;
import com.example.myVirtualSchool.Service.Impl.SubjectService;
import com.example.myVirtualSchool.Service.Impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements ControllerInterface<User> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/create_user")
    public User create(@RequestBody User obj) {
        return userService.create(obj);
    }

    @Override
    @DeleteMapping(path = "/delete_user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @Override
    @GetMapping(path = "/get_users")
    public List<User> listAll() {
        return userService.findAll();
    }

    @Override
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        Optional<User> user = userService.findOne(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<User> fullUpdate(@PathVariable Long id, @RequestBody User obj) {
        Optional<User> optionalSubject = userService.findOne(id);
        if (optionalSubject.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User existingSubject = optionalSubject.get();
        ObjectUpdater.updateFields(existingSubject, obj);

        User updatedSubject = userService.create(existingSubject);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }
}
