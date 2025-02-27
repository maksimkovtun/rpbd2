package com.example.test2.Controllers;

import com.example.test2.App;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class delWindowController {
    @FXML
    public ComboBox tableName;
    @FXML
    public TextField values;
    @FXML
    public Label tableInfo;
    @FXML
    private Button delButton;
    int choice;
    String oldValues;

    @FXML
    private void initialize() {
        tableName.getItems().addAll("Школьник", "Родитель", "Адрес", "Класс", "Оценки", "Предмет");
        tableName.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("Школьник".equals(newValue)) {
                    tableInfo.setText("Школькник(int)");
                    choice = 1;
                } else if ("Родитель".equals(newValue)) {
                    tableInfo.setText("Родитель(int)");
                    choice = 2;
                } else if ("Адрес".equals(newValue)) {
                    tableInfo.setText("Адрес(int)");
                    choice = 3;
                } else if ("Класс".equals(newValue)) {
                    tableInfo.setText("Класс(int)");
                    choice = 4;
                } else if ("Оценки".equals(newValue)) {
                    tableInfo.setText("Оценки(int)");
                    choice = 5;
                } else if ("Предмет".equals(newValue)) {
                    tableInfo.setText("Предмет(int)");
                    choice = 6;
                } else {
                    tableInfo.setText(" ");
                }
            }
        });
    }

    @FXML
    private void delButton() {
        //Schoolboy
        if (choice == 1) {
            String[] schoolboyValues = values.getText().split(",");
            if (schoolboyValues.length == 1) {
                App.delSchoolboy(
                        Integer.parseInt(schoolboyValues[0].trim()));
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
            //Parant
        } else if (choice == 2) {
            String[] parantValues = values.getText().split(",");
            if (parantValues.length == 1) {
                App.delParant(
                        Integer.parseInt(parantValues[0].trim()));
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
            //Address
        } else if (choice == 3) {
            String[] addressValues = values.getText().split(",");
            if (addressValues.length == 1) {
                App.delAddress(
                        Integer.parseInt(addressValues[0].trim()));
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
            //Class_
        } else if (choice == 4) {
            String[] classValues = values.getText().split(",");
            if (classValues.length == 1) {
                App.delClass(
                        Integer.parseInt(classValues[0].trim()));
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
            //Rating
        } else if (choice == 5) {
            String[] ratingValues = values.getText().split(",");
            if (ratingValues.length == 1) {
                App.delRating(
                        Integer.parseInt(ratingValues[0].trim()));
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
            //Subject
        } else if (choice == 6) {
            String[] subjectValues = values.getText().split(",");
            if (subjectValues.length == 1) {
                App.delSubject(
                        Integer.parseInt(subjectValues[0].trim()));
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
        }
    }

    public void setErrorMessageWithDelay(String errorMessage) {
        delButton.setDisable(true);
        Platform.runLater(() -> {
            values.setText(errorMessage);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                values.setText(oldValues);
                delButton.setDisable(false);
            });
            pause.play();
        });
    }
}
