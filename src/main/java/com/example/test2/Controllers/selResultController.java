package com.example.test2.Controllers;

import com.example.test2.App;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

public class selResultController {
    private int choice = selWindowController.getChoice();
    private Timeline updateTimeline;
    @FXML
    public TextArea result;
    @FXML
    public Button selResButton;

    @FXML
    private void selResButton() {
        updateResultText();
    }
    @FXML
    private void initialize() {
        updateResultText();
        updateTimeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> updateResultText()));
        updateTimeline.setCycleCount(Timeline.INDEFINITE);
        updateTimeline.play();
    }
    private void updateResultText() {
        if(choice == 1) {
            result.setText(App.selSchoolboy());
        }else if(choice == 2){
            result.setText(App.selParant());
        }else if(choice == 3){
            result.setText(App.selAddress());
        }else if(choice == 4){
            result.setText(App.selClass());
        }else if(choice == 5){
            result.setText(App.selRating());
        }else if(choice == 6){
            result.setText(App.selSubject());
        }
    }
}
