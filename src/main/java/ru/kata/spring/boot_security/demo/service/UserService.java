package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;


public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(Integer id);

    void deleteUser(Integer id);

    void updateInfo(User user,Integer id);

    User findByUsername(String username);

}
