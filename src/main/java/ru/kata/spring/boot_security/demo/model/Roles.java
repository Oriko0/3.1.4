package ru.kata.spring.boot_security.demo.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String roleType;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Roles() {}

    public Roles(String roleType) {
        this.roleType = roleType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public String getAuthority() {
        return getRoleType();
    }

    @Override
    public String toString() {
        return roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles role = (Roles) o;
        return Objects.equals(getId(), role.getId()) && Objects.equals(getRoleType(), role.getRoleType()) && Objects.equals(getUsers(), role.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleType(), getUsers());
    }


}
