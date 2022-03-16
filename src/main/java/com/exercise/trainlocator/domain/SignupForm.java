package com.exercise.trainlocator.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignupForm {
    
    @NotEmpty
    @Size(min = 4, max = 30)
    private String username = "";

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";
    
    @NotEmpty
    private String email = "";

    @NotEmpty
    @Size(min = 6, max = 30)
    private String password = "";

    @NotEmpty
    @Size(min = 6, max = 30)
    private String passwordCheck = "";

    @NotEmpty
    private String role = "USER";

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
