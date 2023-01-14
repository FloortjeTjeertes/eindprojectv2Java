package com.eindproject.v2.eindprojectv2.logic;

import com.eindproject.v2.eindprojectv2.Main;
import com.eindproject.v2.eindprojectv2.dal.DataBase;
import com.eindproject.v2.eindprojectv2.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserLogic {

  //get all users
  final DataBase dataBase;
    public UserLogic(){
        dataBase = new DataBase();

    }
    //get all users
    public List<User> GetUsers(){
        return Main.dataBase.GetUsers();

    }
    //get user whit filter
    public List<User> GetUsers(User UserToFilter){

        return Main.dataBase.GetUsers(UserToFilter);
    }
    ///help methods
    public boolean CheckAvailableId(int id){
        List<User> users = GetUsers();
        for (User user : users) {
            if(user.getId() == id){
                return false;
            }
        }
        return true;
    }
    public int GetAmountOfUsers(){
        return GetUsers().size();
      }

      //delete user
      public void DeleteUser(User u) {
          Main.dataBase.DeleteUser(u);
        }
        //adds user
    public void AddUser(User User){
        Main.dataBase.AddUser(User);
        }
    public void AddUser(List<User> Users){
        for (User user:Users) {
            Main.dataBase.AddUser(user);
            }

        }
    public void Update(User User) throws IOException{
        Main.dataBase.UpdateUser(User);
        }
    //todo serialising the database
    public void Serialise(List<User>users){
        try {
            File newfile = new File("temp"+File.separator+"UserSave.sav");

            if(!newfile.exists()){
                newfile.createNewFile();

            }
            FileOutputStream fileOut =
            new FileOutputStream("temp"+File.separator+"UserSave.sav");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
         } catch (IOException i) {
            i.printStackTrace();
         }
    }
    //todo deserialising the database
    public List<User>  deserialise(){
        List<User> users = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("temp"+File.separator+"UserSave.sav");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (List<User>) in.readObject();
            in.close();
            fileIn.close();
         } catch (IOException i) {
            i.printStackTrace();
            return users;
         } catch (ClassNotFoundException c) {
            System.out.println("user class not found");
            c.printStackTrace();
            return users;
         }
        return users;
    }
}


