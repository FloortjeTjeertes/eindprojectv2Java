package com.eindproject.v2.eindprojectv2;

import javafx.fxml.FXML;

public class MainController {
    @FXML CollectionController CollectionTabController;
    @FXML MemberController MembersTabController;

    @FXML
    public void update(){
        CollectionTabController.RefreshTable();
        MembersTabController.RefreshTable();
    }

}
