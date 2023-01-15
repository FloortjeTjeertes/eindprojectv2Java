package com.eindproject.v2.eindprojectv2.model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    public String username;
    public String Password;
    private String LastName;
    private String FirstName;
    private int Id;


    public String GetFullName(){
        return FirstName+" "+LastName;
    }
  
    public int getId() {
        return Id;
    }


    public User(String username, String password, String lastName, String firstName, int id, LocalDate birthdate) {
        this.username = username;
        Password = password;
        LastName = lastName;
        FirstName = firstName;
        Id = id;
    }

   
    

}
