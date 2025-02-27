package com.example.test2;

import com.example.test2.Hibernate.HibernateUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppController {
    @FXML
    private Button reConnectButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void addWindow() {
        openWindow("Добавление", "addWindow.fxml");
    }

    @FXML
    private void delWindow() {
        openWindow("Удаление", "delWindow.fxml");
    }

    @FXML
    private void updWindow() {
        openWindow("Редактирование", "updWindow.fxml");
    }

    @FXML
    private void selWindow() { openWindow("Просмотр", "selWindow.fxml");
    }

    @FXML
    private void serWindow() {
        openWindow("Поиск", "serWindow.fxml");
    }

    @FXML
    private void reConnect() throws IOException, URISyntaxException {
        if(!HibernateUtil.isSessionOpen()) {
            HibernateUtil.shutdown();
            HibernateUtil.closeSession();
            Stage newStage = new Stage();
            Stage currentStage = (Stage) reConnectButton.getScene().getWindow();
            App app = new App();
            app.start(newStage);
            currentStage.close();
        }else{
            System.out.println("Сессия итак открыта");
        }
    }

    @FXML
    private void initialize() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::updateStatusLabel, 0, 1, TimeUnit.SECONDS);
    }

    private void updateStatusLabel() {
        Platform.runLater(() -> {
                if (HibernateUtil.isSessionOpen()) {
                    statusLabel.setText("Статус: Подключено");
                    statusLabel.setStyle("-fx-text-fill: #00FF00; -fx-font-weight: bold;");
                } else {
                    statusLabel.setText("Статус: Не подключено");
                    statusLabel.setStyle("-fx-text-fill: #FF0000; -fx-font-weight: bold;");
                }
        });
    }

    public void openWindow(String title, String resource) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResource("images/icon.png")).toURI().toString()));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}