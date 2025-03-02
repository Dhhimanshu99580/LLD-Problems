package parkinglot;

public class Entrance {
    //List<>


    public String findParkingSpot(Vehicle vehicle,ParkingSpotFor2Wheelers parkingSpotFor2Wheelers,ParkingSpotFor4Wheelers parkingSpotFor4Wheelers) {
        String parkingSpot = "";
        if(vehicle.typeOfVehicle==VehicleConstant.TWO_WHEELER) {
            if(parkingSpotFor2Wheelers.getEmpty()) {
                System.out.println("There is no free space for 2 Wheelers...");
                return "NA";
            }
            parkingSpot = parkingSpotFor2Wheelers.findParkingSpotForVehicle(vehicle);
        } else {
            if(parkingSpotFor4Wheelers.getEmpty()) {
                System.out.println("There is no free space for 2 Wheelers...");
                return "NA";
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
