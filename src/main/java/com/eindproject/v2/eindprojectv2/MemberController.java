package com.eindproject.v2.eindprojectv2;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.eindproject.v2.eindprojectv2.logic.UserLogic;
import com.eindproject.v2.eindprojectv2.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class MemberController {
   @FXML
   TableView<User> tableviewUsers;
   @FXML
   TableColumn<User, Integer> tablecolumnId;
   @FXML
   TableColumn<User, String> tablecolumnFirstname;
   @FXML
   TableColumn<User, String> tablecolumnLast;
   @FXML
   TableColumn<User, LocalDateTime> tablecolumnBirthdate;

   // buttons
   @FXML
   Button ButtonAdd;
   @FXML
   Button ButtonEditUser;
   @FXML
   Button ButtonDeleteUser;
   @FXML
   Text TextErrormessage;

   final UserLogic userLogic = new UserLogic();
   List<User> users = userLogic.GetUsers();
   User selectedUser = null;

   @FXML
   public void initialize() {

       tablecolumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
       tablecolumnFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
       tablecolumnLast.setCellValueFactory(new PropertyValueFactory<>("LastName"));
       tablecolumnBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

       FillTableviews(tableviewUsers);
   }

   public void RefreshTable(){
       FillTableviews(tableviewUsers);
   }

   private void FillTableviews(TableView<User> tableviewUsers2) {
       tableviewUsers2.getItems().clear();
       for (User user : users) {
           tableviewUsers2.getItems().add(user);

       }

   }

   @FXML
   public void setSelectedRow() {
       int selectedIndex = tableviewUsers.getSelectionModel().getSelectedIndex();
       // test
       System.out.println(selectedIndex);
       //
       User selected = tableviewUsers.getSelectionModel().getSelectedItem();
       if (Objects.nonNull(selected)) {
           selectedUser = selected;

       } else {
           selectedUser = null;

       }
   }

   @FXML
   public void deleteEvent() {

       try {
           userLogic.DeleteUser(selectedUser);
           users = userLogic.GetUsers();
           TextErrormessage.setText(selectedUser.getFirstName() + " has succesfully been deleted");

           FillTableviews(tableviewUsers);
       } catch (Exception e) {
           TextErrormessage.setText(e.getMessage());
           e.printStackTrace();
       }
   }

   @FXML
   public void editEvent() {
       if (Objects.nonNull(selectedUser)) {

           EditUserController.userToEdit = selectedUser;
           EditUserController.Edit = true;
           try {
               Main.setRoot("User_Create_edit");
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

           EditUserController.userToEdit = null;
           EditUserController.Edit = false;
           Main.setRoot("User_Create_edit");
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }



}
