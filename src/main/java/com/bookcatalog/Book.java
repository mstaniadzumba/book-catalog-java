package com.bookcatalog;

public class Book{
    private int id;
    private String title;
    private String author;

    //contructor without id( for new books before DB insert)
    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    //contructor with id( for books retrieved from DB)
    public Book(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId(){
    return this.id;};

    public void setId(int id){
    }

    public String getTitle(){
        return this.title;
    }

    public void setAuthor(String author) {
            this.author = author;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Override
    public String toString(){
        return title + " by " + author;
    }
}