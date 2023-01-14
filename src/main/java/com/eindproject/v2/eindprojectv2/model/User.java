package com.eindproject.v2.eindprojectv2.model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    public String username;
    public String Password;
    private String LastName;
    private String FirstName;
    private int Id;
    private LocalDate birthdate;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }
    

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

    public String GetFullName(){
        return FirstName+" "+LastName;
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

   

    public User(String username, String password, String lastName, String firstName, int id, LocalDate birthdate) {
        this.username = username;
        Password = password;
        LastName = lastName;
        FirstName = firstName;
        Id = id;
        this.birthdate = birthdate;
    }

   
    

}
