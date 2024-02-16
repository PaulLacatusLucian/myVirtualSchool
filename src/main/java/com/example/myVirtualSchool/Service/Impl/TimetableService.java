package com.example.myVirtualSchool.Service.Impl;

import com.example.myVirtualSchool.Domain.Class;
import com.example.myVirtualSchool.Domain.Student;
import com.example.myVirtualSchool.Domain.Teacher;
import com.example.myVirtualSchool.Domain.Timetable;
import com.example.myVirtualSchool.Repository.ClassRepo;
import com.example.myVirtualSchool.Repository.StudentRepo;
import com.example.myVirtualSchool.Repository.TeacherRepo;
import com.example.myVirtualSchool.Repository.TimetableRepo;
import com.example.myVirtualSchool.Service.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableService implements ServiceInterface<Timetable> {

    private final TimetableRepo timetableRepo;

    public TimetableService(TimetableRepo timetableRepo) {
        this.timetableRepo = timetableRepo;
    }

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private ClassRepo classRepo;

    public Timetable create(Timetable obj) {
        return timetableRepo.save(obj);
    }

    public Timetable create(Timetable obj, Long teacherId, Long classId) {
        Optional<Teacher> teacherOptional = teacherRepo.findById(teacherId);
        if (teacherOptional.isEmpty()) {
            throw new EntityNotFoundException("Teacher was not found.");
        }
        Teacher teacher = teacherRepo.findById(teacherId).orElse(null); // nu se ajunge niciodata pe orElse
        obj.setTeacherEntity(teacher);

        Optional<Class> classOptional = classRepo.findById(classId);
        if (classOptional.isEmpty()) {
            throw new EntityNotFoundException("Class was not found.");
        }
        Class classroom = classRepo.findById(classId).orElse(null); // nu se ajunge niciodata pe orElse
        obj.setClassEntity(classroom);

        return timetableRepo.save(obj);
    }

    @Override
    public List<Timetable> findAll() {
        return (List<Timetable>) timetableRepo.findAll();
    }

    @Override
    public Optional<Timetable> findOne(Long id) {
        return timetableRepo.findById(id);
    }

    @Override
    public boolean ifExist(Long id) {
        return timetableRepo.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if (ifExist(id)) {
            timetableRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Class with ID " + id + " does not exist");
        }
    }
}
