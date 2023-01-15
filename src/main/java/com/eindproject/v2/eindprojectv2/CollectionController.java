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

import com.eindproject.v2.eindprojectv2.model.LendStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class CollectionController {


   @FXML
   TableView<ItemListFormat> tableViewItems;
   @FXML
   TableColumn<ItemListFormat, Integer> tableColumnItemCode;
   @FXML
   TableColumn<ItemListFormat, LendStatus> tableColumnAvailable;
   @FXML
   TableColumn<ItemListFormat, String> tableColumnTitle;
   @FXML
   TableColumn<ItemListFormat, String> tableColumnAuthor;
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

   final DataBase dataBase = Main.dataBase;
   @FXML
   public void initialize() {

       tableColumnItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
       tableColumnAvailable.setCellValueFactory(new PropertyValueFactory<>("LendStatus"));
       tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("titel"));
       tableColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("auteur"));
       TableColumnDateLend.setCellValueFactory(new PropertyValueFactory<>("lendDate"));


       itemLogic = new ItemLogic(dataBase);
        items = itemLogic.GetItems();
      try {
          FillTableviews(tableViewItems);

      }
      catch(Exception e) {
         System.out.println(e.getMessage());
      }
   }

   public void RefreshTable(){
       FillTableviews(tableViewItems);
   }

   private void FillTableviews(TableView<ItemListFormat> tableView) {

       tableView.getItems().clear();
       for (Item item : items) {
           tableView.getItems().add(new ItemListFormat(item));

       }

   }

   @FXML
   public void setSelectedRow() {
       int selectedIndex = tableViewItems.getSelectionModel().getSelectedIndex();
       //test
       System.out.println(selectedIndex);
       //
       ItemListFormat selected = tableViewItems.getSelectionModel().getSelectedItem();
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
            if(item!=null) {
                itemLogic.DeleteItem(item.getItemCode());
                items = itemLogic.GetItems();
                TextErrormessage.setText(((Book) item).getTitle() + " has succesfully been deleted");

                FillTableviews(tableViewItems);
            }
            else {
                TextErrormessage.setText("no item selected");

            }
       } catch (NullPointerException e) {
           TextErrormessage.setText("no item selected");
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
