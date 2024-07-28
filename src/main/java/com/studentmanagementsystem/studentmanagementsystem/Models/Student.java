package com.studentmanagementsystem.studentmanagementsystem.Models;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty email;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty Faculty;

    public Student(String id, String name, String email,  String Faculty, String gender) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.Faculty=new SimpleStringProperty(Faculty);
        this.gender = new SimpleStringProperty(gender);
    }

    // Getters
    public String getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }
    public String getEmail() {
        return email.get();
    }
//    public String getPassword() { return password.get(); }
    public String getFaculty() {
        return Faculty.get();
    }
    public String getGender() {
        return gender.get();
    }

    // Property getters for TableView binding
    public SimpleStringProperty idProperty() {
        return id;
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty facultyProperty() {
        return Faculty;
    }
    public SimpleStringProperty genderProperty() {
        return gender;
    }
}