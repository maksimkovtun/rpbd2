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

import java.util.Objects;

public class updWindowController {
    @FXML
    public ComboBox tableName;
    @FXML
    public ComboBox tableMethod;
    @FXML
    public ComboBox tableField;
    @FXML
    public TextField values;
    @FXML
    public Label tableInfo;
    @FXML
    public Label field;
    @FXML
    private Button updButton;
    int choiceName;
    int choiceMethod = 1;
    int choiceField;
    String oldValues;
    String fieldName = "";
    @FXML
    private void initialize() {
        tableField.setDisable(true);
        tableField.setVisible(false);
        field.setVisible(false);

        tableName.getItems().addAll("Школьник", "Родитель", "Адрес", "Класс", "Оценки", "Предмет");
        tableName.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("Школьник".equals(newValue)) {
                    if(choiceMethod == 1) {
                        tableInfo.setText("Номер(int) | Родитель(int) | Рейтинг(int) \n" +
                                " Фамилия(str) | Имя(str)\n" +
                                " Отчество(str) | Адрес(int) | ГодРождения(int)\n " +
                                " Класс(int) | ГодОкончания(int)");
                    }else if (choiceMethod == 2){
                        tableField.getItems().clear();
                        tableField.getItems().addAll("Номер", "Родитель", "Рейтинг", "Фамилия", "Имя", "Отчество", "Адрес", "ГодРождения", "Класс", "ГодОкончания" );
                        tableInfo.setText("Номер(int) | Новое значение");
                    }
                    choiceName = 1;
                } else if ("Родитель".equals(newValue)) {
                    if(choiceMethod == 1) {
                        tableInfo.setText("Номер(int) | Имя(str) | Фамилия(str) \n" +
                                " Отчество(str) \n" +
                                " Адрес(int) | Статус(str)");
                    }else if (choiceMethod == 2){
                        tableField.getItems().clear();
                        tableField.getItems().addAll("Номер", "Имя", "Фамилия", "Отчество", "Адрес", "Статус");
                        tableInfo.setText("Номер(int) | Новое значение");
                    }
                    choiceName = 2;
                } else if ("Адрес".equals(newValue)) {
                    if(choiceMethod == 1) {
                        tableInfo.setText("Номер(int) | Адрес(str)");
                    }else if (choiceMethod == 2){
                        tableField.getItems().clear();
                        tableField.getItems().addAll("Номер", "Адрес");
                        tableInfo.setText("Номер(int) | Новое значение");
                    }
                    choiceName = 3;
                } else if ("Класс".equals(newValue)) {
                    if(choiceMethod == 1) {
                        tableInfo.setText("Номер(int) | Класс(int)");
                    }else if (choiceMethod == 2){
                        tableField.getItems().clear();
                        tableField.getItems().addAll("Номер", "Класс");
                        tableInfo.setText("Номер(int) | Новое значение");
                    }
                    choiceName = 4;
                } else if ("Оценки".equals(newValue)) {
                    if(choiceMethod == 1) {
                        tableInfo.setText("Номер(int) | Год(int) | Класс(int) \n" +
                                " Предмет(int) \n" +
                                " Четверть(int) | Полугодие(int) | Оцен.Год(int) \n" +
                                " Экзамен(int) | Итоговая(int)");
                    }else if (choiceMethod == 2){
                        tableField.getItems().clear();
                        tableField.getItems().addAll("Номер", "Год", "Класс", "Предмет", "Четверть", "Полугодие", "Оцен.Год", "Экзамен", "Итоговая");
                        tableInfo.setText("Номер(int) | Новое значение");
                    }
                    choiceName = 5;
                } else if ("Предмет".equals(newValue)) {
                    if(choiceMethod == 1) {
                        tableInfo.setText("Номер(int) | Предмет(str)");
                    }else if (choiceMethod == 2){
                        tableField.getItems().clear();
                        tableField.getItems().addAll("Номер", "Предмет");
                        tableInfo.setText("Номер(int) | Новое значение");
                    }
                    choiceName = 6;
                } else {
                    tableInfo.setText(" ");
                }
            }
        });

        tableMethod.getItems().addAll("Полностью", "По полям");
        tableMethod.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ("Полностью".equals(newValue)) {
                    choiceMethod = 1;
                    tableField.setDisable(true);
                    tableField.setVisible(false);
                    field.setVisible(false);
                    tableName.setValue(" ");
                    tableInfo.setText(" ");
                } else if ("По полям".equals(newValue)) {
                    choiceMethod = 2;
                    tableField.setDisable(false);
                    tableField.setVisible(true);
                    field.setVisible(true);
                    tableName.setValue(" ");
                    tableInfo.setText(" ");
                }else {
                    choiceMethod = 1;
                }
            }
        });
        tableField.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //Школьник
                if(choiceName == 1){
                if ("Номер".equals(newValue)) {
                   fieldName = "номер";
                }else if ("Родитель".equals(newValue)) {
                    fieldName = "родитель";
                }else if ("Рейтинг".equals(newValue)) {
                    fieldName = "рейтинг";
                }else if ("Фамилия".equals(newValue)) {
                    fieldName = "фамилия";
                }else if ("Имя".equals(newValue)) {
                    fieldName = "имя";
                }else if ("Отчество".equals(newValue)) {
                    fieldName = "отчество";
                }else if ("Адрес".equals(newValue)) {
                    fieldName = "адрес";
                }else if ("ГодРождения".equals(newValue)) {
                    fieldName = "годрождения";
                }else if ("Класс".equals(newValue)) {
                    fieldName = "класс";
                }else if ("ГодОкончания".equals(newValue)) {
                    fieldName = "годокончания";
                }}else if (choiceName == 2){

                //Родитель
                if ("Номер".equals(newValue)) {
                    fieldName = "номер";
                }else if ("Имя".equals(newValue)) {
                    fieldName = "имя";
                }else if ("Фамилия".equals(newValue)) {
                    fieldName = "фамилия";
                }else if ("Отчество".equals(newValue)) {
                    fieldName = "отчество";
                }else if ("Адрес".equals(newValue)) {
                    fieldName = "адрес";
                }else if ("Статус".equals(newValue)) {
                    fieldName = "статус";
                }}else if (choiceName == 3){

                //Адрес
                if ("Номер".equals(newValue)) {
                    fieldName = "номер";
                }else if ("Адрес".equals(newValue)) {
                    fieldName = "адрес";

                }}else if (choiceName == 4){

                //Класс
                if ("Номер".equals(newValue)) {
                    fieldName = "номер";
                }else if ("Класс".equals(newValue)) {
                    fieldName = "класс";
                }}else if (choiceName == 5){

                //Рейтинг
                if ("Номер".equals(newValue)) {
                    fieldName = "номер";
                }else if ("Год".equals(newValue)) {
                    fieldName = "год";
                }else if ("Класс".equals(newValue)) {
                    fieldName = "класс";
                }else if ("Предмет".equals(newValue)) {
                    fieldName = "предмет";
                }else if ("Четверть".equals(newValue)) {
                    fieldName = "четверть";
                }else if ("Полугодие".equals(newValue)) {
                    fieldName = "полугодие";
                }else if ("Оцен.Год".equals(newValue)) {
                    fieldName = "оценгод";
                }else if ("Экзамен".equals(newValue)) {
                    fieldName = "экзамен";
                }else if ("Итоговая".equals(newValue)) {
                    fieldName = "итоговая";
                }}else if (choiceName == 6){

                //Предмет
                if ("Номер".equals(newValue)) {
                    fieldName = "номер";
                }else if ("Предмет".equals(newValue)) {
                    fieldName = "предмет";
                }} else {
                    tableInfo.setText(" ");
                }
            }
        });
    }

    @FXML
    private void updButton() {
        //Schoolboy
        if (choiceMethod == 1) {
            if (choiceName == 1) {
                String[] schoolboyValues = values.getText().split(",");
                if (schoolboyValues.length == 10) {
                    App.updSchoolboy(
                            Integer.parseInt(schoolboyValues[0].trim()),
                            Integer.parseInt(schoolboyValues[1].trim()),
                            Integer.parseInt(schoolboyValues[2].trim()),
                            schoolboyValues[3].trim(),
                            schoolboyValues[4].trim(),
                            schoolboyValues[5].trim(),
                            Integer.parseInt(schoolboyValues[6].trim()),
                            Integer.parseInt(schoolboyValues[7].trim()),
                            Integer.parseInt(schoolboyValues[8].trim()),
                            Integer.parseInt(schoolboyValues[9].trim()));
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 10");
                }
                //Parant
            } else if (choiceName == 2) {
                String[] parantValues = values.getText().split(",");
                if (parantValues.length == 6) {
                    App.updParant(
                            Integer.parseInt(parantValues[0].trim()),
                            parantValues[1].trim(),
                            parantValues[2].trim(),
                            parantValues[3].trim(),
                            Integer.parseInt(parantValues[4].trim()),
                            parantValues[5].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 6");
                }
                //Address
            } else if (choiceName == 3) {
                String[] addressValues = values.getText().split(",");
                if (addressValues.length == 2) {
                    App.updAddress(
                            Integer.parseInt(addressValues[0].trim()),
                            addressValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
                //Class_
            } else if (choiceName == 4) {
                String[] classValues = values.getText().split(",");
                if (classValues.length == 2) {
                    App.updClass(
                            Integer.parseInt(classValues[0].trim()),
                            classValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
                //Rating
            } else if (choiceName == 5) {
                String[] ratingValues = values.getText().split(",");
                if (ratingValues.length == 9) {
                    App.updRating(
                            Integer.parseInt(ratingValues[0].trim()),
                            Integer.parseInt(ratingValues[1].trim()),
                            Integer.parseInt(ratingValues[2].trim()),
                            Integer.parseInt(ratingValues[3].trim()),
                            Integer.parseInt(ratingValues[4].trim()),
                            Integer.parseInt(ratingValues[5].trim()),
                            Integer.parseInt(ratingValues[6].trim()),
                            Integer.parseInt(ratingValues[7].trim()),
                            Integer.parseInt(ratingValues[8].trim()));
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 9");
                }
                //Subject
            } else if (choiceName == 6) {
                String[] subjectValues = values.getText().split(",");
                if (subjectValues.length == 2) {
                    App.updSubject(
                            Integer.parseInt(subjectValues[0].trim()),
                            subjectValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
            }
        }else if (choiceMethod == 2){
            if (choiceName == 1) {
                String[] schoolboyValues = values.getText().split(",");
                if (schoolboyValues.length == 2) {
                        App.updateField(1,fieldName,Integer.parseInt(schoolboyValues[0].trim()),schoolboyValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
                //Parant
            } else if (choiceName == 2) {
                String[] parantValues = values.getText().split(",");
                if (parantValues.length == 2) {
                        App.updateField(2,fieldName,Integer.parseInt(parantValues[0].trim()),parantValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
                //Address
            } else if (choiceName == 3) {
                String[] addressValues = values.getText().split(",");
                if (addressValues.length == 2) {
                        App.updateField(3,fieldName,Integer.parseInt(addressValues[0].trim()),addressValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
                //Class_
            } else if (choiceName == 4) {
                String[] classValues = values.getText().split(",");
                if (classValues.length == 2) {
                        App.updateField(4,fieldName,Integer.parseInt(classValues[0].trim()),classValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
                //Rating
            } else if (choiceName == 5) {
                String[] ratingValues = values.getText().split(",");
                if (ratingValues.length == 2) {
                        App.updateField(5,fieldName,Integer.parseInt(ratingValues[0].trim()),ratingValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
                //Subject
            } else if (choiceName == 6) {
                String[] subjectValues = values.getText().split(",");
                if (subjectValues.length == 2) {
                        App.updateField(6,fieldName,Integer.parseInt(subjectValues[0].trim()),subjectValues[1].trim());
                } else {
                    oldValues = values.getText();
                    setErrorMessageWithDelay("Ошибка: число аргументов должно быть равно 2");
                }
            }
        }
    }

    private void setErrorMessageWithDelay(String errorMessage) {
        updButton.setDisable(true);
        Platform.runLater(() -> {
            values.setText(errorMessage);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                values.setText(oldValues);
                updButton.setDisable(false);
            });
            pause.play();
        });
    }
}
