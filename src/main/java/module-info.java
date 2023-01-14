module com.eindproject.v.eindprojectv {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.eindproject.v2.eindprojectv2 to javafx.fxml;
    exports com.eindproject.v2.eindprojectv2;
    opens  com.eindproject.v2.eindprojectv2.model to javafx.base;
}