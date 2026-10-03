package Assignment_Project_B;

public class Vehicle {

    private String car;
    private int year;
    private int price;

    public Vehicle(
            String car,
            int year,
            int price) {

        this.car = car;
        this.year = year;
        this.price = price;
    }

    public String getCar() {
        return car;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }
}