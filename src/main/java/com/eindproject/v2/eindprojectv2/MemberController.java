package com.eindproject.v2.eindprojectv2;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.eindproject.v2.eindprojectv2.logic.MemberLogic;
import com.eindproject.v2.eindprojectv2.model.Member;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class MemberController {
   @FXML
   TableView<Member> tableviewMembers;
   @FXML
   TableColumn<Member, Integer> tablecolumnId;
   @FXML
   TableColumn<Member, String> tablecolumnFirstname;
   @FXML
   TableColumn<Member, String> tablecolumnLastName;
   @FXML
   TableColumn<Member, LocalDateTime> tablecolumnBirthdate;

   // buttons
   @FXML
   Button ButtonAdd;
   @FXML
   Button ButtonEditMember;
   @FXML
   Button ButtonDeleteMember;
   @FXML
   Text TextErrormessage;

   final MemberLogic memberLogic = new MemberLogic();
   List<Member> members = memberLogic.GetMembers();
   Member selectedMember = null;

   @FXML
   public void initialize() {

       tablecolumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
       tablecolumnFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
       tablecolumnLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
       tablecolumnBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

       FillTableviews(tableviewMembers);
   }

   public void RefreshTable(){
       FillTableviews(tableviewMembers);
   }

   private void FillTableviews(TableView<Member> tableviewMember2) {
       tableviewMember2.getItems().clear();
       for (Member member : members) {
           tableviewMember2.getItems().add(member);

       }

   }

   @FXML
   public void setSelectedRow() {
       int selectedIndex = tableviewMembers.getSelectionModel().getSelectedIndex();
       // test
       System.out.println(selectedIndex);
       //
       Member selected = tableviewMembers.getSelectionModel().getSelectedItem();
       if (Objects.nonNull(selected)) {
           selectedMember = selected;

       } else {
           selectedMember = null;

       }
   }

   @FXML
   public void deleteEvent() {

       try {
           if(selectedMember !=null) {
               memberLogic.DeleteMember(selectedMember);
               members = memberLogic.GetMembers();
               TextErrormessage.setText(selectedMember.getFirstName() + " has succesfully been deleted");
           }
           else {
               TextErrormessage.setText("no item selected");

           }
               FillTableviews(tableviewMembers);
       } catch (Exception e) {
           TextErrormessage.setText(e.getMessage());
           e.printStackTrace();
       }
   }

   @FXML
   public void editEvent() {
       if (Objects.nonNull(selectedMember)) {

           EditMemberController.memberToEdit = selectedMember;
           EditMemberController.Edit = true;
           try {
               Main.setRoot("Member_Create_edit");
           } catch (IOException e) {
               TextErrormessage.setText(e.getMessage());
               e.printStackTrace();
           }
       } else {
           TextErrormessage.setText("Did you select a book to edit?");
       }
   }

   @FXML
   public void AddEvent() {

       try {
            EditMemberController.id = memberLogic.GetHighestId()+1;
           EditMemberController.memberToEdit = null;
           EditMemberController.Edit = false;
           Main.setRoot("Member_Create_edit");
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }



}
