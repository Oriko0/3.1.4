package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Roles;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public Init( PasswordEncoder passwordEncoder, RoleService roleService, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userService = userService;
    }
    @PostConstruct
    private void init() {
        if (roleService.getRoleByRoleType("ROLE_ADMIN") == null) {
            Roles adminRole = new Roles("ROLE_ADMIN");
            roleService.saveRole(adminRole);
        }
        if (roleService.getRoleByRoleType("ROLE_USER") == null) {
            Roles userRole = new Roles("ROLE_USER");
            roleService.saveRole(userRole);
        }
        if (userService.findByUsername("admin") == null) {
            User adminUser = new User("Tim","Tim","SPB","admin");
            Roles adminRole = roleService.getRoleByRoleType("ROLE_ADMIN");
            Set<Roles> roles = new HashSet<>();
            roles.add(adminRole);
            adminUser.setRoles(roles);
            adminUser.setPassword("admin");
            userService.saveUser(adminUser);
        }
        if (userService.findByUsername("user") == null) {
            User user = new User("Anton","Anton","SPB","user");
            Roles userRole = roleService.getRoleByRoleType("ROLE_USER");
            Set<Roles> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
            user.setPassword("user");
            userService.saveUser(user);
        }
    }
}

