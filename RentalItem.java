package Assignment_Project_B;

public class RentalItem {
    private Vehicle vehicle;
    private int days;

    public RentalItem(
            Vehicle vehicle,
            int days) {

        this.vehicle = vehicle;

        this.days = days;
    }

    public Vehicle getVehicle() {

        return vehicle;
    }

    public String getCar() {

        return vehicle.getCar();
    }

    public int getPrice() {

        return vehicle.getPrice();
    }

    public int getDays() {

        return days;
    }

    public void setDays(int days) {

        this.days = days;
    }

    public int getTotal() {

        return days * vehicle.getPrice();
    }
}