package ru.kata.spring.boot_security.demo.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getShow(){
        return "admin";
    }

}
