package com.eindproject.v2.eindprojectv2;


import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.eindproject.v2.eindprojectv2.dal.DataBase;
import com.eindproject.v2.eindprojectv2.logic.ItemLogic;
import com.eindproject.v2.eindprojectv2.model.Book;
import com.eindproject.v2.eindprojectv2.model.UserSesion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class LendRecieveController {
    DataBase dataBase = Main.dataBase;
    @FXML AnchorPane LendRecievePane;

    @FXML TextField TextFieldItemCode;

    @FXML TextField TextFieldMemberIdentifier;

    @FXML TextField TextFieldItemCodeRecieve;

    @FXML Button ButtonLendItem;

    @FXML Button ButtonRecieveItem;

    @FXML VBox VboxPannel1;

    @FXML VBox VboxPannel2;

    @FXML Text textboxErrorMessage;

    @FXML Text textboxErrorMessageRecieve;

    @FXML Label LabelWelcome;

    protected ItemLogic itemLogic;
    

    UserSesion userSesion = UserSesion.GetInstance();

    @FXML public void initialize(){
        String message ="test";
        try {
            UserSesion userSesion = UserSesion.GetInstance();
            message = userSesion.getUser().GetFullName();

        } catch (Exception e) {
            e.printStackTrace();
        }
        LabelWelcome.setText(LabelWelcome.getText()+" "+message);
        

         itemLogic = new ItemLogic(dataBase);
    }
   
    @FXML public void RecieveItem(){
        
        try {
            int id = Integer.parseInt(TextFieldItemCodeRecieve.getText());

            Book updateditem =(Book)itemLogic.RecieveItem(id);
            long days = Math.round(updateditem.getLendDate().until(LocalDateTime.now(), ChronoUnit.DAYS));
            int daystoolate =(int) (days-21);
            double fine = daystoolate*0.10;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            Button Pay = new Button("Pay Fine");
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ButtonRecieveItem.setDisable(false);
                    VboxPannel2.getChildren().remove(Pay);
                    textboxErrorMessage.setText("");
                }
            };
            Pay.setOnAction(event);
            if(days>=21){
                textboxErrorMessageRecieve.setText("boek:"+updateditem.getTitle()+"has been handed in "+daystoolate+" days too late\n"+
                "Total fine : € "+decimalFormat.format(fine));
                ButtonRecieveItem.setDisable(true);
                VboxPannel2.getChildren().add(Pay);

            }else{
            textboxErrorMessageRecieve.setText("boek:"+updateditem.getTitle()+"has been handed in");
            }

        } catch (Exception e) {
            // TODO: handle exception
            textboxErrorMessageRecieve.setText(e.getMessage());

        }
    }

   @FXML public void LoanItems(){


    try {
        int id = Integer.parseInt(TextFieldItemCode.getText());
        Book updateditem =(Book)itemLogic.LoanItem(id,userSesion.getUser());
        textboxErrorMessage.setText("boek:"+updateditem.getTitle()+" is gereserveerd");

    } catch (Exception e) {

        textboxErrorMessage.setText(e.getMessage());
    }


   }




}
