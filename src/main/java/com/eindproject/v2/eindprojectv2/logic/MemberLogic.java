package com.eindproject.v2.eindprojectv2.logic;

import com.eindproject.v2.eindprojectv2.Main;
import com.eindproject.v2.eindprojectv2.dal.DataBase;
import com.eindproject.v2.eindprojectv2.model.Member;


import java.util.List;

public class MemberLogic {

  //get all Members
  final DataBase dataBase;
    public MemberLogic(){
        dataBase = new DataBase();

    }
    //get all Members
    public List<Member> GetMembers(){
        return Main.dataBase.GetMembers();

    }

    ///help methods
    public int GetHighestId(){
        int HighestID=0;
        List<Member> Members = GetMembers();
        for (Member Member : Members) {
           if(HighestID>=Member.getId()){
               HighestID=Member.getId();
            }
        }
        return HighestID;
    }

    //delete Member
      public void DeleteMember(Member m) {
          Main.dataBase.DeleteMember(m);
        }
        //adds Member
    public void AddMember(Member Member){
        Main.dataBase.AddMember(Member);
        }

    public void Update(Member Member) {
        Main.dataBase.UpdateMember(Member);
        }


}


