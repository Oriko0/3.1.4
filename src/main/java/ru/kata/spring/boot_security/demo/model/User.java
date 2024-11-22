package ru.kata.spring.boot_security.demo.model;




import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "city")
    private String city;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userrole",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Cascade({CascadeType.MERGE,CascadeType.PERSIST, CascadeType.SAVE_UPDATE})
    private Set<Role> roles;

    public User() {}

    public User(String name, String surname, String city, String username) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.username = username;
    }

    public  User(String name, String surname, String city, String username, String password, Set<Role> role) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.username = username;
        this.password = password;
        this.roles = role;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getName(), user.getName()) && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getCity(), user.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getCity());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
    public String getStringRoles(){
        StringBuilder stringRoles = new StringBuilder();
        roles
                .stream()
                .map(Role::getRoleType)
                .map(role -> role.replaceAll("ROLE_", ""))
                .forEach(role -> stringRoles.append(role).append(" "));
        return stringRoles.toString();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
