package com.example.myVirtualSchool.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<O> {

    List<O> findAll();

    Optional<O> findOne(Long id);

    boolean ifExist(Long id);

    void delete(Long id);
}
