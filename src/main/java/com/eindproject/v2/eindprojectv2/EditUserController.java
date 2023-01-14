package com.eindproject.v2.eindprojectv2;

import java.io.IOException;
import java.time.LocalDate;

import com.eindproject.v2.eindprojectv2.logic.UserLogic;
import com.eindproject.v2.eindprojectv2.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EditUserController {


   @FXML
   TextField TextFieldLastName;
   @FXML
   TextField TextFieldFirstName;
   @FXML
   DatePicker DatePickerBirthdate;
   @FXML
   TextField TextFieldUserName;
   @FXML
   TextField TextFieldPassword;
   @FXML
   Text TextErrorMessage;
   static public Boolean Edit = false; //state of screen. true is edit-mode, false is create-mode
   static public User userToEdit; //the user to edit
   final UserLogic userLogic = new UserLogic();

   @FXML
   public void initialize() {
       if (Edit) {
           FillFields(userToEdit);
           TextFieldUserName.setDisable(true);
           TextFieldPassword.setDisable(true);
       }

   }

   private User FillUserCreate() {
       String firstName = TextFieldFirstName.getText();
       String lastName = TextFieldLastName.getText();
       LocalDate birthday = DatePickerBirthdate.getValue();

       String Username = TextFieldUserName.getText();
       String Password = TextFieldPassword.getText();
       int id = 0;

       return new User(Username, Password, lastName, firstName, id, birthday);
   }

   private User FillUserUpdate(User update) {
       String FirstName = TextFieldFirstName.getText();
       String Lastname = TextFieldLastName.getText();
       LocalDate birthdate = DatePickerBirthdate.getValue();

       update.setFirstName(FirstName);
       update.setLastName(Lastname);
       update.setBirthdate(birthdate);
       return update;
   }

   private void FillFields(User user) {

       TextFieldFirstName.setText(user.getFirstName());
       TextFieldLastName.setText(user.getLastName());
       DatePickerBirthdate.setValue(user.getBirthdate());
       TextFieldUserName.setText(user.username);
       TextFieldPassword.setText(user.Password);
   }

   private boolean ValiDateFields() {
       Boolean validated = true;

       if (TextFieldFirstName.getText() == "") {
           TextFieldFirstName.setStyle("-fx-border-color:red");
           validated = false;
       } else {
           TextFieldFirstName.setStyle("-fx-border-color:grey");

       }
       if (TextFieldLastName.getText() == "") {
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
       if (TextFieldUserName.getText() == "") {
           TextFieldUserName.setStyle("-fx-border-color:red");
           validated = false;

       } else {
           TextFieldUserName.setStyle("-fx-border-color:grey");
       }
       if (TextFieldPassword.getText() == "") {
           TextFieldPassword.setStyle("-fx-border-color:red");
           validated = false;

       } else {
           TextFieldPassword.setStyle("-fx-border-color:grey");
       }

       return validated;
   }

   public void Submit() throws IOException {
       if (Edit) {
           if (ValiDateFields()) {
               System.out.println("submit " + userToEdit.getFirstName());
               // make udpdate in logic layer
               try {
                   User update = FillUserUpdate(userToEdit);
                   userLogic.Update(update);
                   Main.setRoot("Main");

               } catch (IOException e) {
                   TextErrorMessage.setText(e.getMessage());
               }
           }
       }
       if (!Edit) {
           // FillUserCreate();
           if (ValiDateFields()) {
               User newUser = FillUserCreate();
               try {
                   userLogic.AddUser(newUser);
                   Main.setRoot("Main");

               } catch (IOException e) {
                   TextErrorMessage.setText(e.getMessage());
               }
               System.out.println("edit " + ValiDateFields());
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
