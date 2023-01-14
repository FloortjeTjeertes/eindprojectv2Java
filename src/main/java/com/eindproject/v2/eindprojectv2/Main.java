package com.eindproject.v2.eindprojectv2;

import com.eindproject.v2.eindprojectv2.dal.DataBase;
import com.eindproject.v2.eindprojectv2.logic.DbLogic;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

        private static Scene scene;
        public static DataBase dataBase;
        private final DbLogic dbLogic = new DbLogic();
        @Override
        public void start(Stage stage) throws IOException {
            try{
                dataBase = dbLogic.LoadDB();
            }
            catch (FileNotFoundException e){
                System.out.println("Database whas not found creating new Database");
                dataBase = new DataBase();
                dbLogic.SaveDB(dataBase);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            scene = new Scene(loadFXML("Login"), 640, 480);
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    dbLogic.SaveDB(dataBase);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            stage.show();
        }
        public static void setRoot(String fxml) throws IOException {
            Parent parent = loadFXML(fxml);
            scene.setRoot(parent);

        }

        private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        }

        public static void main(String[] args) {
            launch();
        }



}