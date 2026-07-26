package parkinglot;

public class Cash implements Payment {
    private PricingStrategy pricingStrategy;

    public Cash(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public String process(Vehicle vehicle, int hoursParked) {
        double totalAmount = pricingStrategy.calculatePrice(vehicle, hoursParked);
        return String.format("Cash payment of Rs. %.2f collected for vehicle %s.",
                totalAmount, vehicle.getVehicleNumber());
    }
}
