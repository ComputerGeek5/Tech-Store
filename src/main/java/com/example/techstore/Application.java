package com.example.techstore;

import com.example.techstore.view.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.techstore.util.Constant.appTitle;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
//        AdminView view = new AdminView();
        HomeView view = new HomeView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setTitle(appTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}