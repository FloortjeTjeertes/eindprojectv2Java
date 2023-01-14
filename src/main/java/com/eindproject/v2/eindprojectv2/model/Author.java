package com.eindproject.v2.eindprojectv2.model;

import java.io.Serializable;

public class Author implements Serializable {
    String firstname;
    String lastname;
    String prefix = null;



    
    public Author(String firstname, String lastname, String prefix) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.prefix = prefix;
    }
    public String getFirstName() {
        return firstname;
    }
    public String getLastName() {
        return lastname;
    }
    public String getPrefix() {
        return prefix;
    }
    
}
