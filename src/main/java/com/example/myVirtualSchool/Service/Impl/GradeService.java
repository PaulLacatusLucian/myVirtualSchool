package com.example.myVirtualSchool.Service.Impl;
import com.example.myVirtualSchool.Domain.Class;
import com.example.myVirtualSchool.Domain.Grade;
import com.example.myVirtualSchool.Domain.Student;
import com.example.myVirtualSchool.Domain.Subject;
import com.example.myVirtualSchool.Repository.ClassRepo;
import com.example.myVirtualSchool.Repository.GradeRepo;
import com.example.myVirtualSchool.Repository.StudentRepo;
import com.example.myVirtualSchool.Repository.SubjectRepo;
import com.example.myVirtualSchool.Service.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService implements ServiceInterface<Grade> {

    private final GradeRepo gradeRepo;

    public GradeService(GradeRepo gradeRepo) {
        this.gradeRepo = gradeRepo;
    }

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private StudentRepo studentRepo;

    public Grade create(Grade obj, Long studentId,  Long subjectId) {
        Optional<Student> studentOptional = studentRepo.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new EntityNotFoundException("Student was not found.");
        }

        Student student = studentRepo.findById(studentId).orElse(null); // nu se ajunge niciodata pe orElse
        obj.setStudent(student);

        Optional<Subject> subjectOptional = subjectRepo.findById(subjectId);
        if (subjectOptional.isEmpty()) {
            throw new EntityNotFoundException("Subject was not found.");
        }

        Subject subject  = subjectRepo.findById(subjectId).orElse(null); // nu se ajunge niciodata pe orElse
        obj.setSubjectEntity(subject);

        return gradeRepo.save(obj);
    }

    public Grade create(Grade obj) {
        return gradeRepo.save(obj);
    }

    @Override
    public List<Grade> findAll() {
        return (List<Grade>) gradeRepo.findAll();
    }

    @Override
    public Optional<Grade> findOne(Long id) {
        return gradeRepo.findById(id);
    }

    @Override
    public boolean ifExist(Long id) {
        return gradeRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if (ifExist(id)) {
            gradeRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Class with ID " + id + " does not exist");
        }
    }
}
