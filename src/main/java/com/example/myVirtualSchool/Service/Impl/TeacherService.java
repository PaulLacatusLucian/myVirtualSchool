package com.example.myVirtualSchool.Service.Impl;

import com.example.myVirtualSchool.Domain.Student;
import com.example.myVirtualSchool.Domain.Subject;
import com.example.myVirtualSchool.Domain.Teacher;
import com.example.myVirtualSchool.Repository.SubjectRepo;
import com.example.myVirtualSchool.Repository.TeacherRepo;
import com.example.myVirtualSchool.Service.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ServiceInterface<Teacher> {

    private final TeacherRepo teacherRepo;

    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public Teacher create(Teacher obj) {
        return teacherRepo.save(obj);
    }

    @Autowired
    private SubjectRepo subjectRepo;

    public Teacher create(Teacher obj, Long subjectId) {
        Optional<Subject> subjectOptional = subjectRepo.findById(subjectId);
        if (subjectOptional.isEmpty()) {
            throw new EntityNotFoundException("Subject was not found.");
        }

        Subject subject = subjectRepo.findById(subjectId).orElse(null); // nu se ajunge niciodata pe orElse
        obj.setSubjectTaught(subject);
        return teacherRepo.save(obj);
    }

    @Override
    public List<Teacher> findAll() {
        return (List<Teacher>) teacherRepo.findAll();
    }

    @Override
    public Optional<Teacher> findOne(Long id) {
        return teacherRepo.findById(id);
    }

    @Override
    public boolean ifExist(Long id) {
        return teacherRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if (ifExist(id)) {
            teacherRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Class with ID " + id + " does not exist");
        }
    }
}
