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

public class addWindowController {
    @FXML
    public ComboBox tableName;
    @FXML
    public TextField values;
    @FXML
    public Label tableInfo;
    @FXML
    private Button addButton;
    int choice;
    String oldValues;

    @FXML
    private void initialize() {
        tableName.getItems().addAll("Школьник", "Родитель", "Адрес", "Класс", "Оценки", "Предмет");
        tableName.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("Школьник".equals(newValue)) {
                    tableInfo.setText("Фамилия(Родитель) | Имя(Родитель) \n" +
                            "| Фамилия(Школьник) | Имя(Школьник)\n" +
                            " Отчество(Школьник) | Адрес | ГодРождения\n " +
                            " Класс");
                    choice = 1;
                } else if ("Родитель".equals(newValue)) {
                    tableInfo.setText("Имя | Фамилия | Отчество \n" +
                            " Адрес | Статус");
                    choice = 2;
                } else if ("Адрес".equals(newValue)) {
                    tableInfo.setText("Адрес");
                    choice = 3;
                } else if ("Класс".equals(newValue)) {
                    tableInfo.setText("Класс");
                    choice = 4;
                } else if ("Оценки".equals(newValue)) {
                    tableInfo.setText("Год | Класс | Предмет \n" +
                            " Четверть | Полугодие | Оцен.Год \n" +
                            " Экзамен | Итоговая");
                    choice = 5;
                } else if ("Предмет".equals(newValue)) {
                    tableInfo.setText("Предмет");
                    choice = 6;
                } else {
                    tableInfo.setText(" ");
                }
            }
        });
    }

    @FXML
    private void addButton() {
        //Schoolboy
        if (choice == 1) {
            String[] schoolboyValues = values.getText().split(",");
            if (schoolboyValues.length == 8) {
                App.addSchoolboy(
                        schoolboyValues[0].trim(),
                        schoolboyValues[1].trim(),
                        schoolboyValues[2].trim(),
                        schoolboyValues[3].trim(),
                        schoolboyValues[4].trim(),
                        schoolboyValues[5].trim(),
                        Integer.parseInt(schoolboyValues[6].trim()),
                        schoolboyValues[7].trim());
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 8");
            }
        //Parant
        } else if (choice == 2) {
            String[] parantValues = values.getText().split(",");
            if (parantValues.length == 5) {
                App.addParant(
                        parantValues[0].trim(),
                        parantValues[1].trim(),
                        parantValues[2].trim(),
                        Integer.parseInt(parantValues[3].trim()),
                        parantValues[4].trim());
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 5");
            }
        //Address
        } else if (choice == 3) {
            String[] addressValues = values.getText().split(",");
            if (addressValues.length == 1) {
                App.addAddress(
                        addressValues[0].trim());
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
        //Class_
        } else if (choice == 4) {
            String[] classValues = values.getText().split(",");
            if (classValues.length == 1) {
                App.addClass(
                        classValues[0].trim());
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
        //Rating
        } else if (choice == 5) {
            String[] ratingValues = values.getText().split(",");
            if (ratingValues.length == 7) {
                App.addRating(
                        Integer.parseInt(ratingValues[0].trim()),
                        Integer.parseInt(ratingValues[1].trim()),
                        Integer.parseInt(ratingValues[2].trim()),
                        Integer.parseInt(ratingValues[3].trim()),
                        Integer.parseInt(ratingValues[4].trim()),
                        Integer.parseInt(ratingValues[5].trim()),
                        Integer.parseInt(ratingValues[6].trim()));
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 7");
            }
        //Subject
        } else if (choice == 6) {
            String[] subjectValues = values.getText().split(",");
            if (subjectValues.length == 1) {
                App.addSubject(
                        subjectValues[0].trim());
            } else {
                oldValues = values.getText();
                setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 1");
            }
        }
    }

    private void setErrorMessageWithDelay(String errorMessage) {
        addButton.setDisable(true);
        Platform.runLater(() -> {
            values.setText(errorMessage);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                values.setText(oldValues);
                addButton.setDisable(false);
            });
            pause.play();
        });
    }
}

