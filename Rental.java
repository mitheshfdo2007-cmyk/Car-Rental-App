package Assignment_Project_B;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Rental {

    private ObservableList<RentalItem> rentalList = FXCollections.observableArrayList();

    public ObservableList<RentalItem> getRentalList() {
        return rentalList;
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (RentalItem item : rentalList) {
            subtotal += item.getTotal();
        }
        return subtotal;
    }

    public double getTax() {
        return getSubtotal() * 0.10;
    }

    public double getTotal() {
        return getSubtotal() + getTax();
    }
}