package com.studentmanagementsystem.studentmanagementsystem.Models;

public class Question {
    private String name;
    private String id;
    private String que1;
    private String que2;
    private String que3;
    private String que4;

    public Question(String name, String id, String que1, String que2, String que3, String que4) {
        this.name = name;
        this.id = id;
        this.que1 = que1;
        this.que2 = que2;
        this.que3 = que3;
        this.que4 = que4;
    }

    // Getters
    public String getName() {

        return this.name;
    }

    public String getId() {

        return this.id;
    }

    public String getQue1() {

        return this.que1;
    }

    public String getQue2() {

        return this.que2;
    }

    public String getQue3() {

        return this.que3;
    }

    public String getQue4() {

        return this.que4;
    }
}

