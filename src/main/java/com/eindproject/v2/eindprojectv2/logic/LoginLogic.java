package com.eindproject.v2.eindprojectv2.logic;

import com.eindproject.v2.eindprojectv2.model.User;
import com.eindproject.v2.eindprojectv2.model.UserSession;

import java.util.List;

public class LoginLogic {
    UserLogic userLogic = new UserLogic();
    public boolean validateLogin(String UserName,String PassWord){

        List<User> users =  userLogic.GetUsers();

        for (User user : users) {
            System.out.println(user.username+"  "+UserName);
            System.out.println(user.Password+"  "+PassWord);

            if (user.username.equals(UserName) && user.Password.equals(PassWord)){
               UserSession session= UserSession.GetInstance();
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
