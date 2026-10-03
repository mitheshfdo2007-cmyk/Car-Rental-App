package Assignment_Project_B;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VehicleRentalApp extends Application {

    @Override
    public void start(Stage stage) {
        // Model
        Rental rental = new Rental();
        // Controller
        RentalController controller = new RentalController(rental);
        // Views
        VehicleSelectionView ui1 = new VehicleSelectionView(controller);
        RentalSummaryView ui2 = new RentalSummaryView(controller);
        PaymentConfirmationView ui3 = new PaymentConfirmationView(controller);
        // Connects view to the Controller
        controller.setViews(
                ui1,
                ui2,
                ui3);
        controller.setStage(stage);
        Scene scene = new Scene(ui1, 600, 500);
        stage.setTitle("BMW Vehicle Rental System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}