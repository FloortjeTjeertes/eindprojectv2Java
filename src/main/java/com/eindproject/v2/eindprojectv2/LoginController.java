package com.eindproject.v2.eindprojectv2;

import com.eindproject.v2.eindprojectv2.dal.DataBase;
import com.eindproject.v2.eindprojectv2.logic.LoginLogic;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;


public class LoginController {
    @FXML TextField textFieldUserName;
    @FXML TextField textFieldPassword;
    @FXML Text TextMessage;

    final DataBase dataBase = Main.dataBase;
    @FXML
    private void CheckLogin() throws IOException {
        LoginLogic login = new LoginLogic();



        if(login.validateLogin(textFieldUserName.getText(),textFieldPassword.getText())){
            System.out.println("work");
            Main.setRoot("Main");

        }
        else{
            textFieldUserName.setStyle("-fx-background-color:red");
            textFieldPassword.setStyle("-fx-background-color:red");
            TextMessage.setFill(Color.RED);
            TextMessage.setText("Invalid username/password combination");

        }




    }
}
