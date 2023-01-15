package com.eindproject.v2.eindprojectv2;

import java.io.IOException;
import java.time.LocalDateTime;

import com.eindproject.v2.eindprojectv2.logic.ItemLogic;
import com.eindproject.v2.eindprojectv2.model.Author;
import com.eindproject.v2.eindprojectv2.model.Book;
import com.eindproject.v2.eindprojectv2.model.LendStatus;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import static java.lang.Integer.parseInt;

public class EditbookController {
   @FXML
   TextField TextFieldIsbn;
   @FXML
   TextField TextFieldTitel;
   @FXML
   TextField TextBoxFirstName;
   @FXML
   TextField TextBoxPrefix;
   @FXML
   TextField TextBoxLastName;
    @FXML
    Text TextErrorMessage;

    static public boolean Edit  = false; //status of screen. true means the screen is used to edit an item  false means it is used to create a new item.
   static public Book itemToEdit;
   final ItemLogic itemLogic = new ItemLogic(Main.dataBase);
    public int id = 0;
   @FXML
   public void initialize() {
       if (Edit) {
           FillFields(itemToEdit);

       }

   }

   private Book FillBookCreate() {
       @SuppressWarnings("DuplicatedCode") int isbn = parseInt(TextFieldIsbn.getText());
       String titel = TextFieldTitel.getText();
       String firstName = TextBoxFirstName.getText();
       String preFix = TextBoxPrefix.getText();
       String lastName = TextBoxLastName.getText();

       Author author = new Author(firstName, preFix, lastName);

       int id =0;


       return new Book(id, null, LocalDateTime.MIN, LendStatus.No, isbn, author, titel);
   }

   private Book FillBookUpdate(Book update) {
       @SuppressWarnings("DuplicatedCode") int isbn = parseInt(TextFieldIsbn.getText());
       String titel = TextFieldTitel.getText();
       String firstName = TextBoxFirstName.getText();
       String preFix = TextBoxPrefix.getText();
       String lastName = TextBoxLastName.getText();

       Author author = new Author(firstName, preFix, lastName);

       return new Book(update.getItemCode(), update.getLender(), update.getLendDate(), update.getLendstatus(),
               isbn, author, titel);
   }

   private void FillFields(Book book) {
       TextFieldIsbn.setText(Long.toString(book.getIsbn()));
       TextFieldTitel.setText(book.getTitle());
       TextBoxFirstName.setText(book.getAuthor().getFirstName());
       TextBoxPrefix.setText(book.getAuthor().getPrefix());
       TextBoxLastName.setText(book.getAuthor().getLastName());

   }

   private boolean ValidateFields() {
       boolean validated = true;
       if (TextFieldIsbn.getText() == "") {
           TextFieldIsbn.setStyle("-fx-border-color:red");
           validated = false;
       } else {
           try {
               Long.parseLong(TextFieldIsbn.getText());
               TextFieldIsbn.setStyle("-fx-border-color:grey");
           }
           catch(Exception e){
               TextFieldIsbn.setStyle("-fx-border-color:red");
               validated = false;
           }
       }
       if (TextFieldTitel.getText() == "") {
           TextFieldTitel.setStyle("-fx-border-color:red");
           validated = false;
       } else {
           TextFieldTitel.setStyle("-fx-border-color:grey");
       }
       if (TextBoxFirstName.getText() == "") {
           TextBoxFirstName.setStyle("-fx-border-color:red");
           validated = false;
       } else {
           TextBoxFirstName.setStyle("-fx-border-color:grey");
       }
       if (TextBoxLastName.getText() == "") {
           TextBoxLastName.setStyle("-fx-border-color:red");
           validated = false;

       } else {
           TextBoxLastName.setStyle("-fx-border-color:grey");
       }
       if (TextBoxPrefix.getText() == "") {
           TextBoxPrefix.setStyle("-fx-border-color:red");
           validated = false;

       } else {
           TextBoxPrefix.setStyle("-fx-border-color:grey");
       }

       return validated;
   }

   public void Submit() {
       if (Edit) {
           if (ValidateFields()) {
               System.out.println("submit " + itemToEdit.getTitle());

               try {
                   itemToEdit.setItemCode(id);
                   Book update = FillBookUpdate(itemToEdit);
                   itemLogic.Update(update);
                   Main.setRoot("Main");

               } catch (IOException e) {
                   TextErrorMessage.setText(e.getMessage());
               }
           }
       }
       if (!Edit) {
           // FillBookCreate();
           if (ValidateFields()) {
          Book newBook =FillBookCreate();
           try {
               itemLogic.AddItem(newBook);
               Main.setRoot("Main");

           } catch (IOException e) {
               TextErrorMessage.setText(e.getMessage());
           }
           System.out.println("edit " + ValidateFields());
           }

       }
   }
   public void Cancel(){
       try {
           Main.setRoot("Main");
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }
}
