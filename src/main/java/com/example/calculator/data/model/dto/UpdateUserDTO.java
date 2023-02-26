package com.example.calculator.data.model.dto;
import com.example.calculator.data.base_entities.UserRoleEntity;

import java.util.List;

public class UpdateUserDTO {

    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private List<UserRoleEntity> userRoles;


    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public List<UserRoleEntity> getUserRoles() {

        return userRoles;
    }

    public void setUserRoles(List<UserRoleEntity> userRoles) {

        this.userRoles = userRoles;
    }

}
