package com.eindproject.v2.eindprojectv2.model;

import java.time.LocalDateTime;

public class Book extends Item{

    private final long isbn;
    private final Author author;
    private final String title;
    

    public Book(int itemCode, User lender, LocalDateTime lendDate, LendStatus lendstatus, long isbn2, Author author,
                String title) {
        super(itemCode, lender, lendDate, lendstatus);
        this.isbn = isbn2;
        this.author = author;
        this.title = title;
    }


    public long getIsbn() {
        return isbn;
    }


    public Author getAuthor() {
        return author;
    }


    public String getTitle() {
        return title;
    }
    
    
}
