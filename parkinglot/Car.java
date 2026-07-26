package parkinglot;

public class Car implements Vehicle{
    private String vehicleNumber;
    public Car(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    @Override
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}
