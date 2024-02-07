package com.example.myVirtualSchool.Service.Impl;

import com.example.myVirtualSchool.Domain.Class;
import com.example.myVirtualSchool.Repository.ClassRepo;
import com.example.myVirtualSchool.Service.ServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService implements ServiceInterface<Class> {

    private final ClassRepo classRepo;

    public ClassService(ClassRepo repo) {
        this.classRepo = repo;
    }

    public Class create(Class obj) {
        return classRepo.save(obj);
    }

    @Override
    public List<Class> findAll() {
        return (List<Class>) classRepo.findAll();
    }

    @Override
    public Optional<Class> findOne(Long id) {
        return classRepo.findById(id);
    }

    @Override
    public boolean ifExist(Long id) {
        return classRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if (ifExist(id)) {
            classRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Class with ID " + id + " does not exist");
        }
    }
}
