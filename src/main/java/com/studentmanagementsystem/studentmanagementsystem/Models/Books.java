package com.studentmanagementsystem.studentmanagementsystem.Models;

public class Books {
    private String bookid;
    private String bookname;
    private String author;
    private String genre;
    private String status;

    public Books( String bookid, String bookname, String author, String genre, String status){
        this.bookid = bookid;
        this.bookname = bookname;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }
    public String getBookid() {

        return bookid;
    }
    public String getBookname() {

        return bookname;
    }
    public String getAuthor() {

        return author;
    }
    public String getGenre() {

        return genre;
    }
    public String getStatus() {

        return status;
    }
}
