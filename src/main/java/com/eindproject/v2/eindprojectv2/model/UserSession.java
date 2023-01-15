package com.eindproject.v2.eindprojectv2.model;

public class UserSession {
    private static UserSession sesionInstance;
    private User user;
    

    

    public User getUser() throws Exception {
        if(this.user == null){
            throw new Exception("user unavailable is a user logged in?");
        }
        return user;
    }
    public void setUser(User user) throws Exception {
        if(this.user != null){
            throw new Exception("user is already logged i did you forget to clear the user?");
        }
        this.user = user;
    }

    public static UserSession GetInstance(){
        if (sesionInstance ==null){
            sesionInstance = new UserSession();
        }
        return sesionInstance;
    }
}
