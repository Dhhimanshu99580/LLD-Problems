package parkinglot;

public class CASHPricing implements PricingStrategy {
    private static final double CAR_RATE   = 60.0;
    private static final double BIKE_RATE  = 40.0;
    private static final double TRUCK_RATE = 100.0;

    @Override
    public double calculatePrice(Vehicle vehicle, int hoursParked) {
        double baseRate;
        switch (vehicle.getVehicleType()) {
            case CAR:   baseRate = CAR_RATE;   break;
            case BIKE:  baseRate = BIKE_RATE;  break;
            case TRUCK: baseRate = TRUCK_RATE; break;
            default:    baseRate = CAR_RATE;
        }
        return baseRate * hoursParked;
    }
}
