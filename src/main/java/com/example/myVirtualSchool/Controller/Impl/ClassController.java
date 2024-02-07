package com.example.myVirtualSchool.Controller.Impl;

import com.example.myVirtualSchool.Controller.ControllerInterface;
import com.example.myVirtualSchool.Domain.Class;
import com.example.myVirtualSchool.Helpers.ObjectUpdater;
import com.example.myVirtualSchool.Service.Impl.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClassController implements ControllerInterface<Class> {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping(path = "/create_classroom")
    public Class create(@RequestBody Class obj) {
        return classService.create(obj);
    }

    @Override
    @DeleteMapping(path = "/delete_classroom/{id}")
    public void delete(@PathVariable Long id) {
        classService.delete(id);
    }

    @Override
    @GetMapping(path = "/get_classrooms")
    public List<Class> listAll() {
        return classService.findAll();
    }

    @Override
    @GetMapping(path = "/classroom/{id}")
    public ResponseEntity<Class> get(@PathVariable Long id) {
        Optional<Class> classOptional = classService.findOne(id);
        return classOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Class> fullUpdate(@PathVariable Long id, @RequestBody Class obj) {
        Optional<Class> optionalSubject = classService.findOne(id);
        if (optionalSubject.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Class existingSubject = optionalSubject.get();
        ObjectUpdater.updateFields(existingSubject, obj);

        Class updatedSubject = classService.create(existingSubject);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }
}
