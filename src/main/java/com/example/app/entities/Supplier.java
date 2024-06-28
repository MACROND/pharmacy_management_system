package com.example.app.entities;

/**
 * The Supplier class represents a supplier entity with properties such as id,
 * name, location, and contact information.
 */
public class Supplier {
    private int id;
    private String name;
    private String location;
    private String contact;

    // Constructors

    public Supplier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Supplier(int id, String name, String location, String contact) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.contact = contact;
    }

    public Supplier(String name, String location, String contact) {
        this.name = name;
        this.location = location;
        this.contact = contact;
    }

    // Getters and setters...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Formatting to String

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
