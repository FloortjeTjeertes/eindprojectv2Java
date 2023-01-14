package com.eindproject.v2.eindprojectv2.model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Item implements Serializable {
    private int itemCode;
    private User lender; 
    private LocalDateTime lendDate;
    private Lendstatus lendstatus;
    public int getItemCode() {
        return this.itemCode;
    }
    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }
    public User getLender() {
        return this.lender;
    }
    public void setLender(User lender) {
        this.lender = lender;
    }
    public LocalDateTime getLendDate() {
        return this.lendDate;
    }
    public void setLendDate(LocalDateTime lendDate) {
        this.lendDate = lendDate;
    }
    public Lendstatus getLendstatus() {
        return this.lendstatus;
    }
    public void setLendstatus(Lendstatus lendstatus) {
        this.lendstatus = lendstatus;
    }
    public Item(int itemCode, User lender, LocalDateTime lendDate, Lendstatus lendstatus) {
        this.itemCode = itemCode;
        this.lender = lender;
        this.lendDate = lendDate;
        this.lendstatus = lendstatus;
    }
    
    
}
