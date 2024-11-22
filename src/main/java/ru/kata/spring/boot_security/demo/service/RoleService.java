package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;




public interface RoleService {


    Role getRoleByRoleType(String roleType);

    void saveRole(Role role);
}
