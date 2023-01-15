package com.eindproject.v2.eindprojectv2.logic;

import com.eindproject.v2.eindprojectv2.Main;
import com.eindproject.v2.eindprojectv2.model.User;

import java.util.List;

public class UserLogic {

  //get all users

    //get all users
    public List<User> GetUsers(){
        return Main.dataBase.GetUsers();

    }






}


