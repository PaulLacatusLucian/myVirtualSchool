package com.example.myVirtualSchool.Controller.Impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DisplayController {
    @GetMapping("public/registration")
    public String displayPage() {
        return "login+signup";
    }
}
