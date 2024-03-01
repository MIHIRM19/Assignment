package com.example.assignment.models;

public class User {
    String name;
    String email;
    String dob;
    String pass;
    String role;

    public User() {
    }

    public User(String name, String email, String dob, String pass, String role) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.pass = pass;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
