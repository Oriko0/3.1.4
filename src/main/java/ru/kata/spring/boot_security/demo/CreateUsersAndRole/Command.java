//package ru.kata.spring.boot_security.demo.CreateUsersAndRole;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.Roles;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.repository.RoleRepository;
//import ru.kata.spring.boot_security.demo.repository.UserRepository;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class Command implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//    @Autowired
//    public Command(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.roleRepository = roleRepository;
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        if (roleRepository.findOneByRoleType("ROLE_ADMIN") == null) {
//            Roles adminRole = new Roles("ROLE_ADMIN");
//            roleRepository.save(adminRole);
//        }
//
//        if (roleRepository.findOneByRoleType("ROLE_USER") == null) {
//            Roles userRole = new Roles("ROLE_USER");
//            roleRepository.save(userRole);
//        }
//        if (userRepository.findUserByUsername("admin") == null) {
//            User adminUser = new User("Tim","Tim","SPB","admin");
//            Roles adminRole = roleRepository.findOneByRoleType("ROLE_ADMIN");
//            Set<Roles> roles = new HashSet<>();
//            roles.add(adminRole);
//            adminUser.setRoles(roles);
//            adminUser.setPassword(passwordEncoder.encode("admin"));
//            userRepository.save(adminUser);
//        }
//
//        if (userRepository.findUserByUsername("user") == null) {
//            User user = new User("Anton","Anton","SPB","user");
//            Roles userRole = roleRepository.findOneByRoleType("ROLE_USER");
//            Set<Roles> roles = new HashSet<>();
//            roles.add(userRole);
//            user.setRoles(roles);
//            user.setPassword(passwordEncoder.encode("user"));
//            userRepository.save(user);
//        }
//
//    }
//}
