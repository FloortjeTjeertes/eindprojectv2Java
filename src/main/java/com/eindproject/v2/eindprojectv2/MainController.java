package com.eindproject.v2.eindprojectv2;
import java.io.Console;
import java.net.URL;
import java.util.ResourceBundle;

import com.eindproject.v2.eindprojectv2.dal.DataBase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML CollectionController CollectionTabController;
    @FXML MemberController MembersTabController;

    @FXML
    public void update(){
        CollectionTabController.RefreshTable();
        MembersTabController.RefreshTable();
    }

}
