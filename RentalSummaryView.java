package Assignment_Project_B;

import java.text.NumberFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RentalSummaryView extends VBox {
    private TableView<RentalItem> summaryTable;
    private Label subtotalLabel;
    private Label taxLabel;
    private Label totalLabel;
    private RentalController controller;

    public RentalSummaryView(RentalController controller) {
        this.controller = controller;
        setSpacing(20);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label("Rental Summary");
        titleLabel.setFont(Font.font(24));
        titleLabel.setStyle("-fx-font-weight: bold;");
        summaryTable = new TableView<>();
        // Car column
        TableColumn<RentalItem, String> carColumn = new TableColumn<>("Car");
        carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
        // Days Column
        TableColumn<RentalItem, Integer> daysColumn = new TableColumn<>("Days");
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("days"));
        // Price Column
        TableColumn<RentalItem, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Total Column
        TableColumn<RentalItem, Integer> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        summaryTable.getColumns().addAll(
                carColumn,
                daysColumn,
                priceColumn,
                totalColumn);
        summaryTable.setPrefHeight(300);
        summaryTable.setStyle("-fx-font-size: 13px;");
        subtotalLabel = new Label();
        taxLabel = new Label();
        totalLabel = new Label();
        totalLabel.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: darkgreen;");
        Button backButton = new Button("Back");
        backButton.setPrefWidth(200);
        backButton.setStyle(
                "-fx-background-color: gray;" +
                        "-fx-text-fill: white;");
        backButton.setOnAction(e -> {
            controller.goToVehicleSelection();
        });
        Button removeButton = new Button("Remove Vehicle");
        removeButton.setPrefWidth(200);
        removeButton.setStyle(
                "-fx-background-color: crimson;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;");
        removeButton.setOnAction(e -> {
            RentalItem selectedItem = summaryTable.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                controller.showAlert("Selection Error", "Please select a vehicle to remove.");
                return;
            }
            controller.getRental().getRentalList().remove(selectedItem);
            summaryTable.refresh();
            refreshTable();
            controller.showAlert("Vehicle Removed", "Vehicle removed successfully.");
        });
        Button paymentButton = new Button("Proceed To Payment");
        paymentButton.setPrefWidth(200);
        paymentButton.setStyle(
                "-fx-background-color: darkgreen;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;");
        paymentButton.setOnAction(e -> {
            controller.goToPayment();
        });
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(
                backButton,
                removeButton,
                paymentButton);
        getChildren().addAll(
                titleLabel,
                summaryTable,
                subtotalLabel,
                taxLabel,
                totalLabel,
                buttonBox);
    }

    public void refreshTable() {
        summaryTable.setItems(controller.getRental().getRentalList());
        summaryTable.refresh();
        NumberFormat format = NumberFormat.getInstance();
        double subtotal = controller.getRental().getSubtotal();
        double tax = controller.getRental().getTax();
        double total = controller.getRental().getTotal();
        subtotalLabel.setText("Subtotal: LKR " + format.format(subtotal));
        taxLabel.setText("Tax (10%): LKR " + format.format(tax));
        totalLabel.setText("Final Total: LKR " + format.format(total));
    }
}