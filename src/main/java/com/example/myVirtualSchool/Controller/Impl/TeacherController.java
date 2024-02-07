package com.example.myVirtualSchool.Controller.Impl;

import com.example.myVirtualSchool.Controller.ControllerInterface;
import com.example.myVirtualSchool.Domain.Subject;
import com.example.myVirtualSchool.Domain.Teacher;
import com.example.myVirtualSchool.Helpers.ObjectUpdater;
import com.example.myVirtualSchool.Service.Impl.SubjectService;
import com.example.myVirtualSchool.Service.Impl.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeacherController implements ControllerInterface<Teacher> {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(path = "/create_teacher/{subject_id}")
    public Teacher create(@RequestBody Teacher obj, @PathVariable Long subject_id) {
        return teacherService.create(obj, subject_id);
    }

    @Override
    @DeleteMapping(path = "/delete_teacher/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }

    @Override
    @GetMapping(path = "/get_teachers")
    public List<Teacher> listAll() {
        return teacherService.findAll();
    }

    @Override
    @GetMapping(path = "/teacher/{id}")
    public ResponseEntity<Teacher> get(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.findOne(id);
        return teacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Teacher> fullUpdate(@PathVariable Long id, @RequestBody Teacher obj) {
        Optional<Teacher> optionalTeacher = teacherService.findOne(id);
        if (optionalTeacher.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Teacher existingTeacher = optionalTeacher.get();
        ObjectUpdater.updateFields(existingTeacher, obj);

        Teacher updatedTeacher = teacherService.create(existingTeacher);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }
}
