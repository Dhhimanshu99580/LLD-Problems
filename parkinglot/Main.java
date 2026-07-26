package parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<Integer, VehicleType> vehicleMap = Map.of(
            1, VehicleType.CAR,
            2, VehicleType.BIKE,
            3, VehicleType.TRUCK
    );

    public static void main(String[] args) {
        Floor[] floors = setupParkingLot();
        ParkingLot parkingLot = new ParkingLot("City Parking", "123 Main St", floors);
        new VehicleFactory(vehicleMap);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + parkingLot.getParkingLotName() + "!");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Park a vehicle");
            System.out.println("2. Exit vehicle");
            System.out.println("3. Quit");
            System.out.print("Choice: ");
            int action = scanner.nextInt();

            switch (action) {
                case 1: parkVehicle(scanner, parkingLot); break;
                case 2: exitVehicle(scanner, parkingLot); break;
                case 3: running = false; break;
                default: System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void parkVehicle(Scanner scanner, ParkingLot parkingLot) {
        System.out.println("\nSelect vehicle type:");
        System.out.println("1. Car");
        System.out.println("2. Bike");
        System.out.println("3. Truck");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        VehicleType vehicleType = vehicleMap.get(choice);
        if (vehicleType == null) {
            System.out.println("Invalid vehicle type.");
            return;
        }

        System.out.print("Enter vehicle number: ");
        String vehicleNumber = scanner.next();

        try {
            Ticket ticket = parkingLot.newVehicleEntry(vehicleType, vehicleNumber);
            System.out.println("\n--- Ticket Generated ---");
            System.out.println(ticket.getTicketDetails());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void exitVehicle(Scanner scanner, ParkingLot parkingLot) {
        System.out.print("\nEnter ticket number: ");
        String ticketNumber = scanner.next();

        Ticket ticket = parkingLot.getTicket(ticketNumber);
        if (ticket == null) {
            System.out.println("Ticket not found.");
            return;
        }

        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.print("Choice: ");
        int methodChoice = scanner.nextInt();

        PaymentMethod paymentMethod;
        switch (methodChoice) {
            case 1: paymentMethod = PaymentMethod.CASH; break;
            case 2: paymentMethod = PaymentMethod.CARD; break;
            case 3: paymentMethod = PaymentMethod.UPI;  break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        double amountPaid = parkingLot.vehicleExit(ticket, paymentMethod);
        System.out.printf("Total amount paid: Rs. %.2f%n", amountPaid);
    }

    private static Floor[] setupParkingLot() {
        List<Spot> floor0Spots = new ArrayList<>();
        for (int i = 1; i <= 5;  i++) floor0Spots.add(new Spot(i, SpotType.TWO_WHEELERS));
        for (int i = 6; i <= 10; i++) floor0Spots.add(new Spot(i, SpotType.FOUR_WHEELERS));
        for (int i = 11; i <= 13; i++) floor0Spots.add(new Spot(i, SpotType.SIX_WHEELERS));

        List<Spot> floor1Spots = new ArrayList<>();
        for (int i = 1; i <= 5;  i++) floor1Spots.add(new Spot(i, SpotType.TWO_WHEELERS));
        for (int i = 6; i <= 10; i++) floor1Spots.add(new Spot(i, SpotType.FOUR_WHEELERS));
        for (int i = 11; i <= 13; i++) floor1Spots.add(new Spot(i, SpotType.SIX_WHEELERS));

        return new Floor[]{
                new Floor(0, floor0Spots),
                new Floor(1, floor1Spots)
        };
    }
}
