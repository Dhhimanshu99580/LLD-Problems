package parkinglot;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Entrance entrance = new Entrance();
        ParkingSpotFor2Wheelers parkingSpotFor2Wheelers= new ParkingSpotFor2Wheelers();
        ParkingSpotFor4Wheelers parkingSpotFor4Wheelers = new ParkingSpotFor4Wheelers();
        parkingSpotFor4Wheelers.initalizeDistance();
        parkingSpotFor2Wheelers.setEmpty(false);
        parkingSpotFor4Wheelers.setEmpty(false);
        parkingSpotFor2Wheelers.initalizeDistance();
        Ticket ticketfor2Wheeler = new Ticket();
        Ticket ticketfor4Wheeler = new Ticket();
        Vehicle vehicle = new Vehicle();
        for(int i=0 ;i <100;i++) {
            System.out.println("Do want to add more vehicles to parking lot.... 1 or 2 or 0");
            int isTrue = sc.nextInt();
           switch(isTrue) {
               case 0: break;
               case 1:

                   vehicle.setTypeOfVehicle(VehicleConstant.TWO_WHEELER);
                   vehicle.setVehicleNum("BR06124957");
                   String your2WheelerParkingSpot = entrance.findParkingSpot(vehicle,parkingSpotFor2Wheelers,parkingSpotFor4Wheelers);
                   System.out.println(your2WheelerParkingSpot);
                   System.out.println("Printing the ticket for you...");
                   ticketfor2Wheeler = ticketfor2Wheeler.prepareTicket(your2WheelerParkingSpot);
                   System.out.println(ticketfor2Wheeler.getTicketId() + "+" + ticketfor2Wheeler.getParkingSpot() +"+" + ticketfor2Wheeler.getEntryTime());
                   break;
               case 2:
                   vehicle.setTypeOfVehicle((VehicleConstant.FOUR_WHEELER));
                   vehicle.setVehicleNum("BR01526974");
                   String your4WheelerParkingSpot = entrance.findParkingSpot(vehicle,parkingSpotFor2Wheelers,parkingSpotFor4Wheelers);
                   System.out.println(your4WheelerParkingSpot);
                   System.out.println("Printing the ticket for you...");
                   ticketfor4Wheeler = ticketfor4Wheeler.prepareTicket(your4WheelerParkingSpot);
                   System.out.println(ticketfor4Wheeler.getTicketId() + "+" + ticketfor4Wheeler.getParkingSpot() +"+" + ticketfor4Wheeler.getEntryTime());
                   break;
           }

        }
    }
//    public static ParkingSpotFor2Wheelers findParkingSpotForVehicle(Vehicle vehicle) {
//
//
//
//        return parkingSpot;
//    }
}
