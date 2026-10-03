package Assignment_Project_B;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.text.NumberFormat;

public class PaymentConfirmationView extends VBox {
    private Label totalLabel;
    private ToggleGroup paymentGroup;
    private RentalController controller;

    public PaymentConfirmationView(RentalController controller) {
        this.controller = controller;
        setSpacing(20);
        setPadding(new Insets(30));
        setAlignment(Pos.TOP_CENTER);
        setStyle("-fx-background-color: #f8f8f8;");
        Label titleLabel = new Label("Payment & Confirmation");
        titleLabel.setFont(Font.font(24));
        titleLabel.setStyle("-fx-font-weight: bold;");
        totalLabel = new Label();
        totalLabel.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: darkgreen;");
        Label paymentMethodLabel = new Label("Select Payment Method");
        RadioButton cashButton = new RadioButton("Cash");
        RadioButton cardButton = new RadioButton("Card");
        paymentGroup = new ToggleGroup();
        cashButton.setToggleGroup(paymentGroup);
        cardButton.setToggleGroup(paymentGroup);
        Button confirmButton = new Button("Confirm Rental");
        confirmButton.setPrefWidth(200);
        confirmButton.setStyle(
                "-fx-background-color: darkgreen;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;");
        Button backButton = new Button("Back");
        backButton.setPrefWidth(200);
        backButton.setStyle(
                "-fx-background-color: gray;" +
                        "-fx-text-fill: white;");
        backButton.setOnAction(e -> {
            controller.goToSummary();
        });
        confirmButton.setOnAction(e -> {
            if (paymentGroup.getSelectedToggle() == null) {
                controller.showAlert("Payment Error", "Please select a payment method.");
                return;
            }
            controller.showAlert("Rental Confirmed", "Rental Confirmed Successfully!");
            controller.getRental().getRentalList().clear();
            paymentGroup.selectToggle(null);
            controller.goToVehicleSelection();
        });
        getChildren().addAll(
                titleLabel,
                totalLabel,
                paymentMethodLabel,
                cashButton,
                cardButton,
                confirmButton,
                backButton);
    }

    public void refreshTotal() {
        double total = controller.getRental().getTotal();
        NumberFormat format = NumberFormat.getInstance();
        totalLabel.setText("Final Amount: LKR " + format.format(total));
    }
}