package parkinglot;

import java.time.LocalDateTime;



public class Ticket {
    LocalDateTime entryTime;
    int ticketId;
    ParkingSpotFor2Wheelers parkingSpotFor2Wheelers;

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public ParkingSpotFor2Wheelers getParkingSpot() {
        return parkingSpotFor2Wheelers;
    }

    public void setParkingSpot(ParkingSpotFor2Wheelers parkingSpotFor2Wheelers) {
        this.parkingSpotFor2Wheelers = parkingSpotFor2Wheelers;
    }
}
