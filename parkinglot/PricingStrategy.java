package parkinglot;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, int hoursParked);
}
