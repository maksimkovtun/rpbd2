package com.example.test2.Controllers;

import com.example.test2.AppController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class selWindowController {
    @FXML
    public ComboBox tableName;
    @FXML
    public TextField values;
    @FXML
    public Label tableInfo;
    public static int choice;
    AppController controller = new AppController();

    @FXML
    private void initialize() {
        tableName.getItems().addAll("Школьников", "Родителей", "Адреса", "Классы", "Оценки", "Предметы");
        tableName.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("Школьников".equals(newValue)) {
                    choice = 1;
                } else if ("Родителей".equals(newValue)) {
                    choice = 2;
                } else if ("Адреса".equals(newValue)) {
                    choice = 3;
                } else if ("Классы".equals(newValue)) {
                    choice = 4;
                } else if ("Оценки".equals(newValue)) {
                    choice = 5;
                } else if ("Предметы".equals(newValue)) {
                    choice = 6;
                } else {
                    tableInfo.setText(" ");
                }
            }
        });
    }

    @FXML
    private void selButton() {
        //Schoolboy
        if (choice == 1) {
            controller.openWindow("Школьники", "selResultWindow.fxml");
            //Parant
        } else if (choice == 2) {
            controller.openWindow("Родители", "selResultWindow.fxml");
            //Address
        } else if (choice == 3) {
            controller.openWindow("Адреса", "selResultWindow.fxml");
            //Class_
        } else if (choice == 4) {
            controller.openWindow("Классы", "selResultWindow.fxml");
            //Rating
        } else if (choice == 5) {
            controller.openWindow("Рейтинг", "selResultWindow.fxml");
            //Subject
        } else if (choice == 6) {
            controller.openWindow("Предметы", "selResultWindow.fxml");
        }
    }
    public static int getChoice(){
        return choice;
    }
}
