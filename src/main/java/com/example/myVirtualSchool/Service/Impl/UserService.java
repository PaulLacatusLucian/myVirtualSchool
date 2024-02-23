package com.example.myVirtualSchool.Service.Impl;

import com.example.myVirtualSchool.Domain.Timetable;
import com.example.myVirtualSchool.Domain.User;
import com.example.myVirtualSchool.Repository.TimetableRepo;
import com.example.myVirtualSchool.Repository.UserRepo;
import com.example.myVirtualSchool.Service.ServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements ServiceInterface<User> {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User create(User obj) {
        return userRepo.save(obj);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public boolean ifExist(Long id) {
        return userRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if (ifExist(id)) {
            userRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Class with ID " + id + " does not exist");
        }
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepo.findByUsername(username);
        return password.equals(user.getPassword());
    }

    public Long getUserIdByUsername(String username) {
        User user = userRepo.findByUsername(username);
        return user != null ? user.getId() : null;
    }

    public boolean isUsernameTaken(String username) {
        return userRepo.existsByUsername(username);
    }

}
