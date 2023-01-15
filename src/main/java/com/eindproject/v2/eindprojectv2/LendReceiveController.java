package com.eindproject.v2.eindprojectv2;


import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.eindproject.v2.eindprojectv2.dal.DataBase;
import com.eindproject.v2.eindprojectv2.logic.ItemLogic;
import com.eindproject.v2.eindprojectv2.model.Book;
import com.eindproject.v2.eindprojectv2.model.UserSession;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class LendReceiveController {
    final DataBase dataBase = Main.dataBase;
    @FXML AnchorPane LendReceivePane;

    @FXML TextField TextFieldItemCode;

    @FXML TextField TextFieldMemberIdentifier;

    @FXML TextField TextFieldItemCodeReceive;

    @FXML Button ButtonLendItem;

    @FXML Button ButtonReceiveItem;

    @FXML VBox VboxPannel1;

    @FXML VBox VboxPannel2;

    @FXML Text textboxErrorMessage;

    @FXML Text textboxErrorMessageReceive;

    @FXML Label LabelWelcome;

    protected ItemLogic itemLogic;
    

    final UserSession userSession = UserSession.GetInstance();

    @FXML public void initialize(){
        String message ="test";
        try {
            UserSession userSession = UserSession.GetInstance();
            message = userSession.getUser().GetFullName();

        } catch (Exception e) {
            e.printStackTrace();
        }
        LabelWelcome.setText(LabelWelcome.getText()+" "+message);
        

         itemLogic = new ItemLogic(dataBase);
    }
   
    @FXML public void ReceiveItem(){
        
        try {
            int id = Integer.parseInt(TextFieldItemCodeReceive.getText());

            Book updateditem =(Book)itemLogic.ReceiveItem(id);
            long days = Math.round(updateditem.getLendDate().until(LocalDateTime.now(), ChronoUnit.DAYS));
            int daysToLate =(int) (days-21);
            double fine = daysToLate*0.10;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            Button Pay = new Button("Pay Fine");
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ButtonReceiveItem.setDisable(false);
                    VboxPannel2.getChildren().remove(Pay);
                    textboxErrorMessage.setText("");
                }
            };
            Pay.setOnAction(event);
            if(days>=21){
                textboxErrorMessageReceive.setText("book:"+updateditem.getTitle()+"has been handed in "+daysToLate+" days too late\n"+
                "Total fine : € "+decimalFormat.format(fine));
                ButtonReceiveItem.setDisable(true);
                VboxPannel2.getChildren().add(Pay);

            }else{
            textboxErrorMessageReceive.setText("book:"+updateditem.getTitle()+"has been handed in");
            }

        } catch (Exception e) {
            textboxErrorMessageReceive.setText("The item code you filled in is not correct please try again. ");

        }
    }

   @FXML public void LoanItems(){

    try {
        int id = Integer.parseInt(TextFieldItemCode.getText());
        Book updatedItem =(Book)itemLogic.LoanItem(id, userSession.getUser());
        textboxErrorMessage.setText("book:"+updatedItem.getTitle()+" is reserved");

    } catch (NumberFormatException e) {

        textboxErrorMessage.setText("The id for the item you filled in is not a valid number try again.");
    } catch (Exception e) {
        textboxErrorMessage.setText("The id of the member is not correct please try again.");
    }


   }




}
