package parkinglot;

public class CARDPricing implements PricingStrategy {
    private static final double CAR_RATE   = 50.0;
    private static final double BIKE_RATE  = 30.0;
    private static final double TRUCK_RATE = 80.0;
    private static final double DISCOUNT   = 0.10;

    @Override
    public double calculatePrice(Vehicle vehicle, int hoursParked) {
        double baseRate;
        switch (vehicle.getVehicleType()) {
            case CAR:   baseRate = CAR_RATE;   break;
            case BIKE:  baseRate = BIKE_RATE;  break;
            case TRUCK: baseRate = TRUCK_RATE; break;
            default:    baseRate = CAR_RATE;
        }
        return baseRate * hoursParked * (1 - DISCOUNT);
    }
}
