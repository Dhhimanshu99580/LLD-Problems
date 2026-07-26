package parkinglot;

public class UPIPricing implements PricingStrategy {
    private static final double CAR_RATE   = 50.0;
    private static final double BIKE_RATE  = 30.0;
    private static final double TRUCK_RATE = 80.0;

    @Override
    public double calculatePrice(Vehicle vehicle, int hoursParked) {
        double baseRate;
        double discount;
        switch (vehicle.getVehicleType()) {
            case CAR:   baseRate = CAR_RATE;   discount = 0.10; break;
            case BIKE:  baseRate = BIKE_RATE;  discount = 0.20; break;
            case TRUCK: baseRate = TRUCK_RATE; discount = 0.05; break;
            default:    baseRate = CAR_RATE;   discount = 0.10;
        }
        return baseRate * hoursParked * (1 - discount);
    }
}
