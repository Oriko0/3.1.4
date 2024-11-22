package ru.kata.spring.boot_security.demo.controlers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
}
