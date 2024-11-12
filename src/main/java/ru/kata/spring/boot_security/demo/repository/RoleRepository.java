package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Roles;



public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Roles findOneByRoleType(String type);
}
