package Assignment_Project_B;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VehicleSelectionView extends VBox {

    public VehicleSelectionView(RentalController controller) {
        setSpacing(15);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_LEFT);
        Label titleLabel = new Label("Vehicle Rental System");
        titleLabel.setTextFill(Color.DARKCYAN);
        titleLabel.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;");
        Label subtitleLabel = new Label("Select the vehicles you want to rent");
        subtitleLabel.setFont(Font.font(12));
        subtitleLabel.setTextFill(Color.GREY);
        Label vehicleLabel = new Label("Available Vehicles");
        vehicleLabel.setFont(Font.font(20));
        vehicleLabel.setStyle(
                "-fx-font-weight: bold;");
        TableView<Vehicle> table = new TableView<>();
        TableColumn<Vehicle, String> carCol = new TableColumn<>("Car");
        carCol.setCellValueFactory(new PropertyValueFactory<>("car"));
        TableColumn<Vehicle, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        TableColumn<Vehicle, Integer> priceCol = new TableColumn<>("Rent Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().addAll(
                carCol,
                yearCol,
                priceCol);
        ObservableList<Vehicle> data = FXCollections.observableArrayList(
                new Vehicle("BMW E39 5 Series", 2000, 45000),
                new Vehicle("BMW E46 M3", 2005, 55000),
                new Vehicle("BMW E60 M5", 2010, 70000),
                new Vehicle("BMW F10 M5", 2015, 95000),
                new Vehicle("BMW G30 M5", 2020, 150000),
                new Vehicle("BMW G80 M4", 2021, 180000),
                new Vehicle("BMW XM", 2024, 350000));
        table.setItems(data);
        table.setPrefWidth(355);
        table.setPrefHeight(250);
        yearCol.setPrefWidth(100);
        priceCol.setPrefWidth(100);
        Label rentLabel = new Label("Rent Details");
        rentLabel.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;");
        Label daysLabel = new Label("Rental Days");

        TextField daysField = new TextField();
        daysField.setPromptText("Enter Rental Days");
        Button addButton = new Button("Add To Rental");
        Button summaryButton = new Button("View Rental Summary");
        addButton.setPrefWidth(200);
        summaryButton.setPrefWidth(200);
        addButton.setStyle(
                "-fx-background-color: darkcyan;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;");
        summaryButton.setStyle(
                "-fx-background-color: gray;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;");
        addButton.setOnAction(e -> {
            Vehicle selectedVehicle = table.getSelectionModel().getSelectedItem();
            controller.addVehicleToRental(selectedVehicle, daysField.getText());
            daysField.clear();
        });
        summaryButton.setOnAction(e -> {
            controller.goToSummary();
        });
        VBox formBox = new VBox(15);
        formBox.getChildren().addAll(
                rentLabel,
                daysLabel,
                daysField,
                addButton,
                summaryButton);
        formBox.setPadding(new Insets(20));
        formBox.setStyle(
                "-fx-background-color: #f8f8f8;" +
                        "-fx-padding: 20;" +
                        "-fx-border-color: lightgray;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;");
        HBox mainContent = new HBox(30);
        mainContent.getChildren().addAll(
                table,
                formBox);
        getChildren().addAll(
                titleLabel,
                subtitleLabel,
                vehicleLabel,
                mainContent);
    }
}