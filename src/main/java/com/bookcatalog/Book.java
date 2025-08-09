package com.bookcatalog;

public class Book{
    private String title;
    private String author;

    //contructor
    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    @Override
    public String toString(){
        return title + " by " + author;
    }
}