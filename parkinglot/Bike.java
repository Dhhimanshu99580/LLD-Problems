package parkinglot;

public class Bike implements  Vehicle{
    private String vehicleNumber;
    public Bike(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.BIKE;
    }
    @Override
    public String getVehicleNumber() {
        return vehicleNumber;
    }
}
