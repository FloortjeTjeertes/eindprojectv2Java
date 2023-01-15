package com.eindproject.v2.eindprojectv2.dal;

import com.eindproject.v2.eindprojectv2.model.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBase implements Serializable {

    final List<Member> memberDatabase = new ArrayList<Member>();

    final List<User> userDatabase = new ArrayList<User>();
    final List<Item> itemDatabase = new ArrayList<Item>();
    public DataBase() {


        AddMember(new Member("Tjeertes","Floortje",0, LocalDate.now().plusDays(1))); //add user
        AddMember(new Member("geritsen","henk",1, LocalDate.now().plusDays(2))); //add user

        AddUser(new User("User","StraightA","maan","gerard",2, LocalDate.now().plusDays(3))); //add user

        Author henk = new Author("hank","garry","");
         Author author2 = new Author("henk","en","van");

        AddItem(new Book(0,userDatabase.get(0), LocalDateTime.now().plusDays(1), LendStatus.No,9789027439642L,henk,"Boek1"));
        AddItem(new Book(1,userDatabase.get(0), LocalDateTime.now().plusDays(2), LendStatus.Yes,123456789010L,author2,"Boek2"));

    }
//    adds user to array of users
    public void AddUser(User user){
        userDatabase.add(user);
    }

    public List<User> GetUsers(){
       return this.userDatabase;

    }



    public void AddItem(Item item){
        this.itemDatabase.add(item);
    }
    public List<Item> GetItem(Item itemToUpdate){

        List<Item> items = new ArrayList<Item>();
        for (Item item : this.itemDatabase) {
            if(item.equals(itemToUpdate)){
                items.add(item);
            }
        }
        return items;


    }
    public List<Item> GetItem(){
        return  this.itemDatabase;
    }
    public void UpdateItem(Item item){
        for (int i=0; i < itemDatabase.size(); i++) {
            if(itemDatabase.get(i).getItemCode() == item.getItemCode()){
                itemDatabase.set(i,item);
            }
        }
    }
    public void  DeleteItem(Item item){
        itemDatabase.remove(item);
    }

    public void AddMember(Member member){
        memberDatabase.add(member);
    }
    public List<Member> GetMembers(){
        return this.memberDatabase;

    }
    public List<Member> GetMember(Member memberToUpdate){

        List<Member> members = new ArrayList<Member>();
        for (Member member : this.memberDatabase) {
            if(member.equals(memberToUpdate)){
                members.add(member);
            }
        }
        return members;


    }

    public void UpdateMember(Member member){

        for (int i=0; i < memberDatabase.size(); i++) {
            if(memberDatabase.get(i).getId()==member.getId()){
                memberDatabase.set(i,member);
            }
        }
    }

    public void DeleteMember(Member memberToDelete) {
        memberDatabase.remove(memberToDelete);

    }
}
