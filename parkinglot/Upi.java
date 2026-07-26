package parkinglot;

public class Upi implements Payment {
 PricingStrategy pricingStrategy;
 public Upi(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
    @Override
    public String process(Vehicle vehicle, int hoursParked) {
        double totalAmount = pricingStrategy.calculatePrice(vehicle, hoursParked);
        return "Payment of " + totalAmount + " made successfully for vehicle " + vehicle.getVehicleNumber() + " using UPI.";
    }
}
