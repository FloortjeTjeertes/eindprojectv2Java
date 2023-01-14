package com.eindproject.v2.eindprojectv2;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.eindproject.v2.eindprojectv2.dal.DataBase;
import com.eindproject.v2.eindprojectv2.logic.ItemLogic;
import com.eindproject.v2.eindprojectv2.model.Book;
import com.eindproject.v2.eindprojectv2.model.Item;
import com.eindproject.v2.eindprojectv2.model.ItemListFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class CollectionController {


   @FXML
   TableView<ItemListFormat> tableviewItems;
   @FXML
   TableColumn<ItemListFormat, Long> tablecolumnItemCode;
   @FXML
   TableColumn<ItemListFormat, String> tablecolumnAvailable;
   @FXML
   TableColumn<ItemListFormat, String> tablecolumnTitle;
   @FXML
   TableColumn<ItemListFormat, String> tablecolumnAuthor;
   @FXML
   TableColumn<ItemListFormat,LocalDateTime> TableColumnDateLend;
   // buttons
   @FXML
   Button ButtonAdd;
   @FXML
   Button ButtonEditItem;
   @FXML
   Button ButtonDelete;
   @FXML
   Text TextErrormessage;


   List<Item> items = null;
   static Item item = null;
   ItemLogic itemLogic = null;

   DataBase dataBase = Main.dataBase;
   @FXML
   public void initialize() {

       tablecolumnItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
       tablecolumnAvailable.setCellValueFactory(new PropertyValueFactory<>("lendstatus"));
       tablecolumnTitle.setCellValueFactory(new PropertyValueFactory<>("titel"));
       tablecolumnAuthor.setCellValueFactory(new PropertyValueFactory<>("auteur"));
       TableColumnDateLend.setCellValueFactory(new PropertyValueFactory<>("lendDate"));


       itemLogic = new ItemLogic(dataBase);
        items = itemLogic.GetItems();
      try {
          FillTableviews(tableviewItems);

      }
      catch(Exception e) {
         System.out.println(e.getMessage());
      }
   }

   public void RefreshTable(){
       FillTableviews(tableviewItems);
   }

   private void FillTableviews(TableView<ItemListFormat> tableView) {

       tableView.getItems().clear();
       for (Item item : items) {
           tableView.getItems().add(new ItemListFormat(item));

       }

   }

   @FXML
   public void setSelectedRow() {
       int selectedIndex = tableviewItems.getSelectionModel().getSelectedIndex();
       //test
       System.out.println(selectedIndex);
       //
       ItemListFormat selected = tableviewItems.getSelectionModel().getSelectedItem();
       if(Objects.nonNull(selected)){
       item = selected.getItem();

       //test
       Book book = (Book) item;
       System.out.println(book.getTitle());
       }
       else{
           item= null;
       }
   }

   @FXML
   public void deleteEvent() {

       try {
           itemLogic.DeleteItem(item.getItemCode());
           items = itemLogic.GetItems();
           TextErrormessage.setText(((Book) item).getTitle() + " has succesfully been deleted");

           FillTableviews(tableviewItems);
       } catch (Exception e) {
           TextErrormessage.setText(e.getMessage());
           e.printStackTrace();
       }
   }

   @FXML
   public void editEvent() {
       if (Objects.nonNull(item)) {


           try {
               EditbookController.Edit = true;
               EditbookController.itemToEdit=(Book) item;

               Main.setRoot("Book_Create_edit");
           } catch (IOException e) {
               TextErrormessage.setText(e.getMessage());
               e.printStackTrace();
           }
       }
       else{
           TextErrormessage.setText("Did you select a book to edit?");
       }
   }

   @FXML
   public void AddEvent() {

       try {
           EditbookController.Edit = false;
           EditbookController.itemToEdit=null;
           Main.setRoot("Book_Create_edit");

       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}
