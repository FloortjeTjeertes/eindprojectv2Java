package com.eindproject.v2.eindprojectv2.logic;

import com.eindproject.v2.eindprojectv2.dal.DataBase;
import com.eindproject.v2.eindprojectv2.model.User;
import com.eindproject.v2.eindprojectv2.model.UserSesion;

import java.util.List;

public class LoginLogic {

    public boolean validateLogin(String UserName,String PassWord,DataBase dataBase){

        List<User> users =  dataBase.GetUsers();

        for (User user : users) {
            System.out.println(user.username+"  "+UserName);
            System.out.println(user.Password+"  "+PassWord);

            if (user.username.equals(UserName) && user.Password.equals(PassWord)){
               UserSesion session= UserSesion.GetInstance();
                try {
                    session.setUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }

        return false;
    }

}
