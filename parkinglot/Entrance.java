package parkinglot;

public class Entrance {
    List<>


    public String findParkingSpot(Vehicle vehicle) {
        String parkingSpot = "";
        if(vehicle.typeOfVehicle==VehicleConstant.TWO_WHEELER) {
            ParkingSpotFor2Wheelers parkingSpotFor2Wheelers = new ParkingSpotFor2Wheelers();
            if(!parkingSpotFor2Wheelers.getEmpty()) {
                System.out.println("There is no free space for 2 Wheelers...");
            }
            parkingSpot = parkingSpotFor2Wheelers.findParkingSpotForVehicle(vehicle);
        } else {
            ParkingSpotFor4Wheelers parkingSpotFor4Wheelers = new ParkingSpotFor4Wheelers();
            if(!parkingSpotFor4Wheelers.getEmpty()) {
                System.out.println("There is no free space for 2 Wheelers...");
            }
            parkingSpot = parkingSpotFor4Wheelers.findParkingSpotForVehicle(vehicle);
        }
        return parkingSpot;
    }

    public void updateParkingLot() {

    }

    public void generateTicket() {

    }
}
