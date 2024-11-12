package ru.kata.spring.boot_security.demo.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allusers";
    }
    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("rolesUser", roleRepository.findAll());
        return "user-info";
    }
    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") @Valid User user) {
        userService.saveUser(user);
        return "redirect:/admin";

    }
    @GetMapping("/addNewUpdateUser")
    public String addNewUpdateUser(@RequestParam("usrId") Integer id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-update";
    }
    @GetMapping("/updateInfo")
    public String updateInfo(@ModelAttribute("user") @Valid User user, @RequestParam Integer id) {
        userService.updateInfo(user,id);
        return "redirect:/admin";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("usrId") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
