package com.eindproject.v2.eindprojectv2.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable {

    private String LastName;
    private String FirstName;
    private int Id;
    private LocalDate birthdate;



    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }



    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }



    public Member(String lastName, String firstName, int id, LocalDate birthdate) {

        LastName = lastName;
        FirstName = firstName;
        Id = id;
        this.birthdate = birthdate;
    }

   
    

}
