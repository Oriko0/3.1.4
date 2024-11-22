package ru.kata.spring.boot_security.demo.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Init {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public Init( RoleService roleService, UserService userService, PasswordEncoder passwordEncoder ) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    private void init() {
        if (userService.findByUsername("admin") == null) {
            User adminUser = new User("Tim","Tim","SPB","admin",passwordEncoder.encode("admin"),Set.of(new Role("ROLE_ADMIN")));
            userService.saveInit(adminUser);
        }
        if (userService.findByUsername("user") == null) {
            User user = new User("Anton","Anton","SPB","user",passwordEncoder.encode("user"),Set.of(new Role("ROLE_USER")));
            userService.saveInit(user);
        }
    }
}
