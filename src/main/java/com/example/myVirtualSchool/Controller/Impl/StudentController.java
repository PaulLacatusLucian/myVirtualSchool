package com.example.myVirtualSchool.Controller.Impl;

import com.example.myVirtualSchool.Controller.ControllerInterface;
import com.example.myVirtualSchool.Domain.Student;
import com.example.myVirtualSchool.Helpers.ObjectUpdater;
import com.example.myVirtualSchool.Service.Impl.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController implements ControllerInterface<Student> {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "create_student/{class_id}")
    public Student create(@RequestBody Student obj, @PathVariable Long class_id) {
        return studentService.create(obj, class_id);
    }


    @Override
    @DeleteMapping(path = "/delete_student/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @Override
    @GetMapping(path = "get_students")
    public List<Student> listAll() {
        return studentService.findAll();
    }

    @Override
    @GetMapping(path = "/student/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Optional<Student> student = studentService.findOne(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Student> fullUpdate(@PathVariable Long id, @RequestBody Student obj) {
        Optional<Student> studentOptional = studentService.findOne(id);
        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Student existingStudent = studentOptional.get();
        ObjectUpdater.updateFields(existingStudent, obj);

        Student updatedStudent = studentService.create(existingStudent);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
}
