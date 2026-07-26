package parkinglot;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketNumber;
    private Vehicle vehicle;
    private Spot spot;
    private Floor floor;
    private LocalDateTime entryTime;

    public Ticket(String ticketNumber, Vehicle vehicle, Spot spot, Floor floor, LocalDateTime entryTime) {
        this.ticketNumber = ticketNumber;
        this.vehicle = vehicle;
        this.spot = spot;
        this.floor = floor;
        this.entryTime = entryTime;
    }

    public String getTicketNumber() { return ticketNumber; }
    public Vehicle getVehicle()     { return vehicle; }
    public Spot getSpot()           { return spot; }
    public Floor getFloor()         { return floor; }
    public LocalDateTime getEntryTime() { return entryTime; }

    public String getTicketDetails() {
        return "Ticket Number: " + ticketNumber + "\n" +
               "Vehicle Number: " + vehicle.getVehicleNumber() + "\n" +
               "Vehicle Type:   " + vehicle.getVehicleType() + "\n" +
               "Floor Number:   " + floor.getFloorNumber() + "\n" +
               "Spot Number:    " + spot.getSpotNumber() + "\n" +
               "Entry Time:     " + entryTime;
    }
}
