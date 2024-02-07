package com.example.myVirtualSchool.Service.Impl;
import com.example.myVirtualSchool.Domain.Class;
import com.example.myVirtualSchool.Domain.Student;
import com.example.myVirtualSchool.Repository.ClassRepo;
import com.example.myVirtualSchool.Repository.StudentRepo;
import com.example.myVirtualSchool.Service.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements ServiceInterface<Student> {

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Autowired
    private ClassRepo classRepository;

    @Transactional
    public Student create(Student student, Long classId) {
        Optional<Class> classOptional = classRepository.findById(classId);
        if (classOptional.isEmpty()) {
            throw new EntityNotFoundException("Clasa nu a putut fi găsită.");
        }

        Class classEntity = classRepository.findById(classId).orElse(null); // nu se ajunge niciodata pe orElse
        student.setClassEntity(classEntity);

        return studentRepo.save(student);
    }

    public Student create(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepo.findAll();
    }

    @Override
    public Optional<Student> findOne(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public boolean ifExist(Long id) {
        return studentRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if (ifExist(id)) {
            studentRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Class with ID " + id + " does not exist");
        }
    }
}
