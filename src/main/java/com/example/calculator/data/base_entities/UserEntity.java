package com.example.calculator.data.base_entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String username;

    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    //    @ManyToMany
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> userRoles = new ArrayList<>();

    public String getUsername() {

        return username;
    }

    public UserEntity setUsername(String username) {

        this.username = username;
        return this;
    }

    public String getPassword() {

        return password;
    }

    public UserEntity setPassword(String password) {

        this.password = password;
        return this;
    }

    public List<UserRoleEntity> getUserRoles() {

        return userRoles;
    }

    public UserEntity setUserRoles(List<UserRoleEntity> userRoles) {

        this.userRoles = userRoles;
        return this;
    }

    public UserEntity addRole(UserRoleEntity userRole) {

        this.userRoles.add(userRole);
        return this;
    }

    public String getFirstName() {

        return firstName;
    }

    public UserEntity setFirstName(String firstName) {

        this.firstName = firstName;
        return this;
    }

    public String getLastName() {

        return lastName;
    }

    public UserEntity setLastName(String lastName) {

        this.lastName = lastName;
        return this;
    }

}
