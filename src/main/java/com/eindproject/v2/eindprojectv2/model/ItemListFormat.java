package com.eindproject.v2.eindprojectv2.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class ItemListFormat {
    private Item item;
    public Item getItem() {
        return item;
    }

    public final int itemCode;
    public final LendStatus lendStatus;
    public final String titel;
    public final String auteur;
    public final LocalDateTime lendDate;
    public final double isbn;
    public ItemListFormat(Item item) {
        this.item = item;
        Book book = (Book)item;
        this.itemCode = item.getItemCode();
        this.lendStatus = item.getLendstatus();
        this.titel = book.getTitle();
        this.auteur = book.getAuthor().lastname +" "+book.getAuthor().getFirstName()+" "+book.getAuthor().getPrefix();
        this.lendDate = item.getLendDate();
        this.isbn = book.getIsbn();
    }
    public int getItemCode() {
        return itemCode;
    }
    public LendStatus getLendStatus() {
        return lendStatus;
    }
    public String getTitel() {
        return titel;
    }
    public String getAuteur() {
        return auteur;
    }
    public LocalDateTime getLendDate() {
        if(Objects.nonNull(lendDate) && lendStatus == LendStatus.Yes ){
        return lendDate.plus(3, ChronoUnit.WEEKS);
        }
        else return null;
    }
    public double getIsbn() {
        return isbn;
    }
 

  
    
}
