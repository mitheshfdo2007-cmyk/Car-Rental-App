package Assignment_Project_B;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class RentalController {
    private Rental rental;
    private Stage stage;
    private VehicleSelectionView ui1;
    private RentalSummaryView ui2;
    private PaymentConfirmationView ui3;

    public RentalController(Rental rental) {
        this.rental = rental;
    }

    public Rental getRental() {
        return rental;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setViews(VehicleSelectionView ui1, RentalSummaryView ui2, PaymentConfirmationView ui3) {
        this.ui1 = ui1;
        this.ui2 = ui2;
        this.ui3 = ui3;
    }

    public void goToVehicleSelection() {
        stage.getScene().setRoot(ui1);
    }

    public void goToSummary() {
        ui2.refreshTable();
        stage.getScene().setRoot(ui2);
    }

    public void goToPayment() {
        ui3.refreshTotal();
        stage.getScene().setRoot(ui3);
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addVehicleToRental(Vehicle selectedVehicle, String daysText) {
        if (selectedVehicle == null) {
            showAlert("Selection Error", "Please select a vehicle.");
            return;
        }
        try {
            int days = Integer.parseInt(daysText);
            if (days <= 0) {
                showAlert("Invalid Input", "Rental days must be greater than 0.");
                return;
            }
            boolean vehicleExists = false;
            for (RentalItem item : rental.getRentalList()) {
                if (item.getCar().equals(selectedVehicle.getCar())) {
                    item.setDays(days);
                    vehicleExists = true;
                    showAlert("Rental Updated", "Rental days updated successfully!");

                    break;
                }
            }
            if (!vehicleExists) {
                RentalItem item = new RentalItem(selectedVehicle, days);
                rental.getRentalList().add(item);
                showAlert("Success", "Vehicle Added Successfully!");
            }
        } catch (Exception ex) {
            showAlert("Invalid Input", "Please enter valid rental days.");
        }
    }
}