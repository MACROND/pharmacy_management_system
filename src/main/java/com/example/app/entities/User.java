package com.example.app.entities;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String role;

    // Constructors

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(String id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public User(String id, String firstName, String lastName, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Format to String

    @Override
    public String toString() {
        return "Pharmacist{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

