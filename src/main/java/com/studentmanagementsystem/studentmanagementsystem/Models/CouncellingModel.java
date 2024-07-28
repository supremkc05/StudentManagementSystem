package com.studentmanagementsystem.studentmanagementsystem.Models;

public class CouncellingModel {
    private String name;
    private String id;
    private String problem;
    private String specifycol;

    public CouncellingModel(String name, String id, String problem, String specify) {
        this.name = name;
        this.id = id;
        this.problem = problem;
        this.specifycol = specify;
    }

    public String getName() {

        return name;
    }

    public String getId() {

        return id;
    }

    public String getProblem() {

        return problem;
    }

    public String getSpecify() {

        return specifycol;
    }

}
