package com.eindproject.v2.eindprojectv2.model;

public class UserSesion {
    private static UserSesion sesionInstance;
    private User user;
    

    
    private UserSesion(){

    }

    public User getUser() throws Exception {
        if(this.user == null){
            throw new Exception("user unavailable is a user loged in?");
        }
        return user;
    }
    public void setUser(User user) throws Exception {
        if(this.user != null){
            throw new Exception("user is already loged i did you forget to clear the user?");
        }
        this.user = user;
    }
    public void clearUser() throws Exception {
        if(this.user == null){
            throw new Exception("no user logged in so no need to clear it right?");
        }
        this.user = null;
    }

    public static UserSesion GetInstance(){
        if (sesionInstance ==null){
            sesionInstance = new UserSesion();
        }
        return sesionInstance;
    }
}
