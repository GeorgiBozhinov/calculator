package com.example.calculator.data.model.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterDTO {

    @NotNull
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")
    private String password;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")
    private String confirmPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String creationDate;

    private Integer numberOfFailedLogins;

    private String userStatus;

    public UserRegisterDTO() {

    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getConfirmPassword() {

        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {

        this.confirmPassword = confirmPassword;
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

    public String getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(String creationDate) {

        this.creationDate = creationDate;
    }

    public Integer getNumberOfFailedLogins() {

        return numberOfFailedLogins;
    }

    public void setNumberOfFailedLogins(Integer numberOfFailedLogins) {

        this.numberOfFailedLogins = numberOfFailedLogins;
    }

    public String getUserStatus() {

        return userStatus;
    }

    public void setUserStatus(String userStatus) {

        this.userStatus = userStatus;
    }

}
