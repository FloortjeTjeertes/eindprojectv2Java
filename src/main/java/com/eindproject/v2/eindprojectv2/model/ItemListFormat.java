package com.eindproject.v2.eindprojectv2.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class ItemListFormat {
    private Item item =null;
    public Item getItem() {
        return item;
    }

    public double itemCode;
    public Lendstatus lendstatus;
    public String titel;
    public String auteur;
    public LocalDateTime lendDate;
    public double isbn;
    public ItemListFormat(Item item) {
        this.item = item;
        Book book = (Book)item;
        this.itemCode = item.getItemCode();
        this.lendstatus = item.getLendstatus();
        this.titel = book.getTitle();
        this.auteur = book.getAuteur().lastname +" "+book.getAuteur().getFirstName()+" "+book.getAuteur().getPrefix();
        this.lendDate = item.getLendDate();
        this.isbn = book.getIsbn();
    }
    public double getItemCode() {
        return itemCode;
    }
    public Lendstatus getLendstatus() {
        return lendstatus;
    }
    public String getTitel() {
        return titel;
    }
    public String getAuteur() {
        return auteur;
    }
    public LocalDateTime getLendDate() {
        if(Objects.nonNull(lendDate) && lendstatus == Lendstatus.Yes ){
        return lendDate.plus(3, ChronoUnit.WEEKS);
        }
        else return null;
    }
    public double getIsbn() {
        return isbn;
    }
 

  
    
}
