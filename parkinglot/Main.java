package parkinglot;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        for(int i=0 ;i <100;i++) {
            System.out.println("Do want to add more vehicles to parking lot.... 1 or 0");
            int isTrue = sc.nextInt();
           switch(isTrue) {
               case 0: break;
               default:
                   Vehicle vehicle = new Vehicle();
                   vehicle.setTypeOfVehicle(VehicleConstant.TWO_WHEELER);
                   vehicle.setVehicleNum("BR06124957");
                   ParkingSpotFor2Wheelers parkingSpotFor2Wheelers = findParkingSpotForVehicle(vehicle);
           }

        }
    }
    public static ParkingSpotFor2Wheelers findParkingSpotForVehicle(Vehicle vehicle) {



        return parkingSpot;
    }
}
