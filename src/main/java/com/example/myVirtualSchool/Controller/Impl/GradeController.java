package com.example.myVirtualSchool.Controller.Impl;

import com.example.myVirtualSchool.Controller.ControllerInterface;
import com.example.myVirtualSchool.Domain.Grade;
import com.example.myVirtualSchool.Helpers.ObjectUpdater;
import com.example.myVirtualSchool.Service.Impl.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GradeController implements ControllerInterface<Grade> {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping(path = "/create_grade/{student_id}/{subject_id}")
    public Grade create(@RequestBody Grade obj, @PathVariable Long student_id, @PathVariable Long subject_id) {
        return gradeService.create(obj, student_id, subject_id);
    }

    @Override
    @DeleteMapping(path = "/delete_grade/{id}")
    public void delete(@PathVariable Long id) {
        gradeService.delete(id);
    }

    @Override
    @GetMapping(path = "/get_grades")
    public List<Grade> listAll() {
        return gradeService.findAll();
    }

    @Override
    @GetMapping(path = "/grade/{id}")
    public ResponseEntity<Grade> get(@PathVariable Long id) {
        Optional<Grade> grade = gradeService.findOne(id);
        return grade.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Grade> fullUpdate(@PathVariable Long id, @RequestBody Grade obj) {
        Optional<Grade> optionalSubject = gradeService.findOne(id);
        if (optionalSubject.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Grade existingSubject = optionalSubject.get();
        ObjectUpdater.updateFields(existingSubject, obj);

        Grade updatedSubject = gradeService.create(existingSubject);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }
}
