package com.eindproject.v2.eindprojectv2;

import java.io.IOException;
import java.time.LocalDate;

import com.eindproject.v2.eindprojectv2.logic.MemberLogic;
import com.eindproject.v2.eindprojectv2.model.Member;

import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EditMemberController {


   @FXML
   TextField TextFieldLastName;
   @FXML
   TextField TextFieldFirstName;
   @FXML
   DatePicker DatePickerBirthdate;

   @FXML
   Text TextErrorMessage;
   static public Boolean Edit = false; //state of screen. true is edit-mode, false is create-mode
   static public Member memberToEdit; //the user to edit
   final MemberLogic memberLogic = new MemberLogic();

    public static int id = 0;



    @FXML
   public void initialize() {
       if (Edit) {
           FillFields(memberToEdit);

       }
        LocalDate minDate = LocalDate.now().minusYears(122);
        LocalDate maxDate = LocalDate.now();
        DatePickerBirthdate.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                    }});

   }

   private Member FillMemberCreate() {
       String firstName = TextFieldFirstName.getText();
       String lastName = TextFieldLastName.getText();
       LocalDate birthday = DatePickerBirthdate.getValue();


       int id = 0;

       return new Member(lastName, firstName, id, birthday);
   }

   private Member FillMemberUpdate(Member update) {
       String FirstName = TextFieldFirstName.getText();
       String Lastname = TextFieldLastName.getText();
       LocalDate birthdate = DatePickerBirthdate.getValue();

       update.setFirstName(FirstName);
       update.setLastName(Lastname);
       update.setBirthdate(birthdate);
       return update;
   }

   private void FillFields(Member member) {

       TextFieldFirstName.setText(member.getFirstName());
       TextFieldLastName.setText(member.getLastName());
       DatePickerBirthdate.setValue(member.getBirthdate());

   }

   private boolean ValidateFields() {
       boolean validated = true;

       if (TextFieldFirstName.getText().equals("")) {
           TextFieldFirstName.setStyle("-fx-border-color:red");
           validated = false;
       } else {
           TextFieldFirstName.setStyle("-fx-border-color:grey");

       }
       if (TextFieldLastName.getText().equals("")) {
           TextFieldLastName.setStyle("-fx-border-color:red");
           validated = false;

       } else {
           TextFieldLastName.setStyle("-fx-border-color:grey");
       }
       if (DatePickerBirthdate.getValue() == null) {
           DatePickerBirthdate.setStyle("-fx-border-color:red");
           validated = false;

       } else {
           DatePickerBirthdate.setStyle("-fx-border-color:grey");
       }


       return validated;
   }

   public void Submit() {
       if (Edit) {
           if (ValidateFields()) {
               System.out.println("submit " + memberToEdit.getFirstName());
               // make udpdate in logic layer
               try {
                   Member update = FillMemberUpdate(memberToEdit);
                   memberLogic.Update(update);
                   Main.setRoot("Main");

               } catch (IOException e) {
                   TextErrorMessage.setText(e.getMessage());
               }
           }
       }
       if (!Edit) {
           // FillUserCreate();
           if (ValidateFields()) {

               Member newMember = FillMemberCreate();
               newMember.setId(id+1);

               try {
                   memberLogic.AddMember(newMember);
                   Main.setRoot("Main");

               } catch (IOException e) {
                   TextErrorMessage.setText(e.getMessage());
               }
               System.out.println("edit " + ValidateFields());
           }
       }
   }

   public void Cancel() {
       try {
           Main.setRoot("Main");
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }

}
