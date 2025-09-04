package com.adso.pos.model;


// The Customer class represents a customer in the system.
// It is a data model that holds basic customer information.

public class Customer {
    private int id;
    private String name;
    private String email;

    // Constructor to create a new Customer instance.
    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters for all fields.
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
