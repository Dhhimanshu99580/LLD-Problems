package parkinglot;

import java.util.HashMap;
import java.util.Map;

public class VehicleFactory {
    static Map<Integer, VehicleType> vehicleMap = new HashMap<>();

    public VehicleFactory(Map<Integer, VehicleType> vehicleMap) {
        this.vehicleMap = vehicleMap;
    }

    public static Vehicle createVehicle(int choice, String vehicleNumber) {
        VehicleType vehicleType = vehicleMap.get(choice);
        if (vehicleType == null) throw new IllegalArgumentException("Invalid choice: " + choice);
        return createVehicle(vehicleType, vehicleNumber);
    }

    public static Vehicle createVehicle(VehicleType vehicleType, String vehicleNumber) {
        switch (vehicleType) {
            case CAR:   return new Car(vehicleNumber);
            case BIKE:  return new Bike(vehicleNumber);
            case TRUCK: return new Truck(vehicleNumber);
            default: throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}
