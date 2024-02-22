package com.example.myVirtualSchool.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homepage")
public class Homepage {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from secure endpoint");
    }
}
