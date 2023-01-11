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

   // @Access(AccessType.PROPERTY)
    @ManyToMany(fetch = FetchType.EAGER)
    //@ManyToMany(targetEntity=UserRoleEntity.class)
    private List<UserRoleEntity> userRoles;

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

    @Override
    public String toString() {
        return "UserEntity{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
