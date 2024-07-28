package com.studentmanagementsystem.studentmanagementsystem.Models;

public class Extracurricular {
    private String namecol;
    private String idcol;
    private String levelcol;
    private String sportscol;
    private String teaminfo;
    private String memberscol;

    public Extracurricular(String namecol, String idcol, String levelcol, String sportscol, String teaminfo, String memberscol) {
        this.namecol = namecol;
        this.idcol = idcol;
        this.levelcol = levelcol;
        this.sportscol = sportscol;
        this.teaminfo = teaminfo;
        this.memberscol = memberscol;
    }

    public String getName() {

        return namecol;
    }

    public String getId() {

        return idcol;
    }

    public String getLevel() {

        return levelcol;
    }

    public String getSports() {

        return sportscol;
    }

    public String getTeaminfo() {

        return teaminfo;
    }

    public String getMembers() {

        return memberscol;
    }

    public static Extracurricular fromCsvLine(String csvLine) {
        String[] parts = csvLine.split(",", -1); // Split the CSV line by commas
        String name = parts[0];
        String id = parts[1];
        String level = parts[2];
        String sports = parts[3];
        String teaminfo = parts.length > 4 ? parts[4] : ""; // Check if teaminfo exists
        StringBuilder members = new StringBuilder();

        // Handle members if present
        for (int i = 5; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                if (members.length() > 0) members.append("; ");
                members.append(parts[i]);
            }
        }

        return new Extracurricular(name, id, level, sports, teaminfo, members.toString());
    }
}
