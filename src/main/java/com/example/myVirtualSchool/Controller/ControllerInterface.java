package com.example.myVirtualSchool.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ControllerInterface<O> {
    public void delete(@PathVariable("id") Long id);

    public List<O> listAll();

    public ResponseEntity<O> get(@PathVariable("id") Long id);

    public ResponseEntity<O> fullUpdate(@PathVariable("id") Long id, @RequestBody O obj);

}
