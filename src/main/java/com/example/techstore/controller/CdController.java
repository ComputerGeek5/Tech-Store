package com.example.techstore.controller;

import com.example.techstore.model.CD;
import com.example.techstore.service.CdService;
import com.example.techstore.view.CreateCdView;
import com.example.techstore.view.ManagerView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

import static com.example.techstore.util.Constant.appTitle;

public class CdController {
    private static final CdService cdService;

    static {
        cdService = new CdService();
    }

    public static void back(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ManagerView view = new ManagerView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void add(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        CreateCdView view = new CreateCdView();
        Scene scene = new Scene(view, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static List<CD> getCds() {
        return cdService.getAll();
    }
}
