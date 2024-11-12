package ru.kata.spring.boot_security.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("user"
                ,userService.loadUserByUsername(principal.getName()));
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model, Principal principal) {
        model.addAttribute("user"
                , userService.loadUserByUsername(principal.getName()));
        return "add";
    }

    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") @Valid User user
            ,@RequestParam String[] user_roles) {
        userService.saveUser(user,user_roles);
        return "redirect:/admin";

    }

    @PostMapping(value = "/updateInfo")
    public String updateInfo(@ModelAttribute("user") User user
            , @RequestParam String[] user_roles) {
        userService.saveUser(user,user_roles);
        return "redirect:/admin";
    }

    @PostMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
