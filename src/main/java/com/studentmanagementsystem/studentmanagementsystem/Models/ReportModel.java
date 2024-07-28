package com.studentmanagementsystem.studentmanagementsystem.Models;

public class ReportModel {
    private String problemId;
    private String id;
    private String stdName;
    private String problemtitle;
    private String specify;

    public ReportModel(String problemId, String id, String stdName, String problemtitle,String specify) {
        this.problemId = problemId;
        this.id = id;
        this.stdName = stdName;
        this.problemtitle = problemtitle;
        this.specify = specify;
    }

    public String getProblemId() {
        return problemId;
    }
    public String getId() {
        return id;
    }
    public String getStdName() {
        return stdName;
    }
    public String getProblemtitle() {
        return problemtitle;
    }
    public String getSpecify() {
        return specify;
    }
}