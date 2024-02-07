package com.example.myVirtualSchool.Repository;

import com.example.myVirtualSchool.Domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);
}
