package com.studentmanagementsystem.studentmanagementsystem.Models;

public class Teacher {
    private String tid;
    private String teachername;
    private String teacheremail;
//    private String tpass;
    private String number;
    private String faculty;

    public Teacher(String tid, String teachername, String teacheremail, String number, String faculty) {
        this.tid = tid;
        this.teachername = teachername;
        this.teacheremail = teacheremail;
//        this.tpass = tpass;
        this.number = number;
        this.faculty = faculty;
    }

    public Teacher() {
    }

    public String getTid() {

        return this.tid;
    }

//    public String getTpass() {
//        return this.tpass;
//    }

    public Object getTeachername() {

        return this.teachername;
    }

    public Object getTeacheremail() {

        return this.teacheremail;
    }

    public Object getNumber() {

        return this.number;
    }

    public Object getFaculty() {

        return this.faculty;
    }
}
