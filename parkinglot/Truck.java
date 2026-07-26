package parkinglot;

public class Truck implements Vehicle {
    private String vehicleNumber;

    public Truck(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.TRUCK;
    }
}
