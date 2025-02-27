package com.example.test2.Controllers;

import com.example.test2.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class serWindowController {
    @FXML
    public TextField request;
    @FXML
    private Button serButton;
    @FXML
    private TextArea result;
    App app = new App();
    @FXML
    private void serButton() {
      result.setText(app.search(request.getText()));
    }
}

