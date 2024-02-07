package com.example.myVirtualSchool.Controller.Impl;

import com.example.myVirtualSchool.Controller.ControllerInterface;
import com.example.myVirtualSchool.Domain.Subject;
import com.example.myVirtualSchool.Helpers.ObjectUpdater;
import com.example.myVirtualSchool.Service.Impl.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController implements ControllerInterface<Subject> {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(path = "/create_subject")
    public Subject create(@RequestBody Subject obj) {
        return subjectService.create(obj);
    }

    @Override
    @DeleteMapping(path = "/delete_subject/{id}")
    public void delete(@PathVariable Long id) {
        subjectService.delete(id);
    }

    @Override
    @GetMapping(path = "/get_subjects")
    public List<Subject> listAll() {
        return subjectService.findAll();
    }

    @Override
    @GetMapping(path = "/subject/{id}")
    public ResponseEntity<Subject> get(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.findOne(id);
        return subject.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Subject> fullUpdate(@PathVariable Long id, @RequestBody Subject obj) {
        Optional<Subject> optionalSubject = subjectService.findOne(id);
        if (optionalSubject.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Subject existingSubject = optionalSubject.get();
        ObjectUpdater.updateFields(existingSubject, obj);

        Subject updatedSubject = subjectService.create(existingSubject);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }
}
