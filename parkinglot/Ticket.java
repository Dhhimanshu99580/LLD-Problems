package parkinglot;

import java.time.LocalDateTime;



public class Ticket {
    LocalDateTime entryTime;
    double ticketId;
    String parkingSpotFor2Wheelers;

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public double getTicketId() {
        return ticketId;
    }

    public void setTicketId(double ticketId) {
        this.ticketId = ticketId;
    }

    public String getParkingSpot() {
        return parkingSpotFor2Wheelers;
    }

    public void setParkingSpot(String parkingSpotFor2Wheelers) {
        this.parkingSpotFor2Wheelers = parkingSpotFor2Wheelers;
    }

    public Ticket prepareTicket(String parkingSpot) {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setTicketId(Math.random());
        ticket.setParkingSpot(parkingSpot);
        return ticket;
    }
}
