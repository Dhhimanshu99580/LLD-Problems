package parkinglot;

public class Vehicle {
    VehicleConstant typeOfVehicle;
    String vehicleNum;

    public VehicleConstant getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(VehicleConstant typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }
}
