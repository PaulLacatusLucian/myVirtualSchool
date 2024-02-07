package com.example.myVirtualSchool.Service.Impl;
import com.example.myVirtualSchool.Domain.Subject;
import com.example.myVirtualSchool.Repository.SubjectRepo;
import com.example.myVirtualSchool.Service.ServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements ServiceInterface<Subject> {

    private final SubjectRepo subjectRepo;

    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public Subject create(Subject obj) {
        return subjectRepo.save(obj);
    }

    @Override
    public List<Subject> findAll() {
        return (List<Subject>) subjectRepo.findAll();
    }

    @Override
    public Optional<Subject> findOne(Long id) {
        return subjectRepo.findById(id);
    }

    @Override
    public boolean ifExist(Long id) {
        return subjectRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if (ifExist(id)) {
            subjectRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Class with ID " + id + " does not exist");
        }
    }
}
