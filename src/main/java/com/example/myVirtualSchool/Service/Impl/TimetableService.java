package com.example.myVirtualSchool.Service.Impl;
import com.example.myVirtualSchool.Domain.Timetable;
import com.example.myVirtualSchool.Repository.TimetableRepo;
import com.example.myVirtualSchool.Service.ServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableService implements ServiceInterface<Timetable> {

    private final TimetableRepo timetableRepo;

    public TimetableService(TimetableRepo timetableRepo) {
        this.timetableRepo = timetableRepo;
    }

    public Timetable create(Timetable obj) {
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
