package com.studentmanagementsystem.studentmanagementsystem.Models;

public class AdmissionOfficer {
    private String id;
//    private String password;
    private String name;
    private String email;
    private String number;

    public AdmissionOfficer(String id, String name, String email, String number) {
        this.id = id;
//        this.password = password;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public AdmissionOfficer() {
    }


    public String getId() {
        return this.id;
    }


    public String getName() {

        return this.name;
    }

    public String getEmail() {

        return this.email;
    }

    public String getNumber() {

        return this.number;
    }

    public void setId(String id) {

        this.id = id;
    }


    public void setName(String name) {

        this.name = name;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setNumber(String number) {

        this.number = number;
    }
}
