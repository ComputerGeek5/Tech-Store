package com.example.techstore.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SupplierView extends VBox {

    private final AnchorPane anchorPane;
    private final Button back;
    private final TableView tableView;
    private final TableColumn tableColumn;
    private final TableColumn tableColumn0;
    private final TableColumn tableColumn1;
    private final TableColumn tableColumn2;
    private final Label label;
    private final Label label0;

    public SupplierView() {

        anchorPane = new AnchorPane();
        back = new Button();
        tableView = new TableView();
        tableColumn = new TableColumn();
        tableColumn0 = new TableColumn();
        tableColumn1 = new TableColumn();
        tableColumn2 = new TableColumn();
        label = new Label();
        label0 = new Label();

        setPrefHeight(600.0);
        setPrefWidth(1000.0);
        getStyleClass().add("main");
        getStylesheets().add("/com/example/techstore/view/store/../..//static/style.css");

        VBox.setVgrow(anchorPane, javafx.scene.layout.Priority.ALWAYS);
        anchorPane.setPrefWidth(1000.0);

        back.setLayoutX(14.0);
        back.setLayoutY(14.0);
        back.setMnemonicParsing(false);
        back.setOnAction(this::store);
        back.setPrefHeight(40.0);
        back.setPrefWidth(200.0);
        back.getStyleClass().add("button-primary");
        back.setText("Back");

        tableView.setLayoutX(14.0);
        tableView.setLayoutY(168.0);
        tableView.setPrefHeight(420.0);
        tableView.setPrefWidth(970.0);

        tableColumn.setPrefWidth(304.0);
        tableColumn.setText("Name");

        tableColumn0.setMinWidth(0.0);
        tableColumn0.setPrefWidth(294.0);
        tableColumn0.setText("Category");

        tableColumn1.setMinWidth(0.0);
        tableColumn1.setPrefWidth(285.0);
        tableColumn1.setText("Price");

        tableColumn2.setMinWidth(0.0);
        tableColumn2.setPrefWidth(86.0);
        tableColumn2.setText("Action");

        label.setLayoutX(442.0);
        label.setLayoutY(80.0);
        label.getStyleClass().add("font-secondary");
        label.setText("Items");

        label0.setLayoutX(816.0);
        label0.setLayoutY(14.0);
        label0.getStyleClass().add("font-primary");
        label0.setText("Supplier");

        anchorPane.getChildren().add(back);
        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn0);
        tableView.getColumns().add(tableColumn1);
        tableView.getColumns().add(tableColumn2);
        anchorPane.getChildren().add(tableView);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        getChildren().add(anchorPane);

    }

    private void store(javafx.event.ActionEvent actionEvent) {

    }
}
