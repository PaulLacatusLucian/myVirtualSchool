package com.example.myVirtualSchool.Controller.Impl;

import com.example.myVirtualSchool.Controller.ControllerInterface;
import com.example.myVirtualSchool.Domain.Timetable;
import com.example.myVirtualSchool.Helpers.ObjectUpdater;
import com.example.myVirtualSchool.Service.Impl.TimetableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TimetableController implements ControllerInterface<Timetable> {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @PostMapping(path = "/create_timetable/{teacher_id}/{class_id}")
    public Timetable create(@RequestBody Timetable obj, @PathVariable Long teacher_id,  @PathVariable Long class_id) {
        return timetableService.create(obj, teacher_id, class_id);
    }

    @Override
    @DeleteMapping(path = "/delete_timetable/{id}")
    public void delete(@PathVariable Long id) {
        timetableService.delete(id);
    }

    @Override
    @GetMapping(path = "/get_timetables")
    public List<Timetable> listAll() {
        return timetableService.findAll();
    }

    @Override
    @GetMapping(path = "/timetable/{id}")
    public ResponseEntity<Timetable> get(@PathVariable Long id) {
        Optional<Timetable> subject = timetableService.findOne(id);
        return subject.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Timetable> fullUpdate(@PathVariable Long id, @RequestBody Timetable obj) {
        Optional<Timetable> optionalSubject = timetableService.findOne(id);
        if (optionalSubject.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Timetable existingSubject = optionalSubject.get();
        ObjectUpdater.updateFields(existingSubject, obj);

        Timetable updatedSubject = timetableService.create(existingSubject);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }
}
