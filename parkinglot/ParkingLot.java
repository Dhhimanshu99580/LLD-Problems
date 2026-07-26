package parkinglot;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private String parkingLotName;
    private String parkingLotAddress;
    private Floor[] floors;
    private Map<String, Ticket> activeTickets;
    private int ticketCounter = 0;

    public ParkingLot(String parkingLotName, String parkingLotAddress, Floor[] floors) {
        this.parkingLotName = parkingLotName;
        this.parkingLotAddress = parkingLotAddress;
        this.floors = floors;
        this.activeTickets = new HashMap<>();
    }

    public String getParkingLotName() { return parkingLotName; }

    public Ticket newVehicleEntry(VehicleType vehicleType, String vehicleNumber) {
        Vehicle vehicle = VehicleFactory.createVehicle(vehicleType, vehicleNumber);
        for (Floor floor : floors) {
            if (floor.isFloorAvailable(vehicleType)) {
                Spot spot = floor.getAvailableSpot(vehicleType);
                spot.parkVehicle(vehicle);
                Ticket ticket = generateTicket(vehicle, floor, spot);
                activeTickets.put(ticket.getTicketNumber(), ticket);
                return ticket;
            }
        }
        throw new RuntimeException("Parking full — no available spot for " + vehicleType);
    }

    public Ticket generateTicket(Vehicle vehicle, Floor floor, Spot spot) {
        String ticketNumber = "TKT-" + (++ticketCounter);
        return new Ticket(ticketNumber, vehicle, spot, floor, LocalDateTime.now());
    }

    public double vehicleExit(Ticket ticket, PaymentMethod paymentMethod) {
        long hours = ChronoUnit.HOURS.between(ticket.getEntryTime(), LocalDateTime.now());
        int hoursParked = (int) Math.max(hours, 1); // minimum 1-hour charge

        Payment payment = createPayment(paymentMethod);
        String receipt = payment.process(ticket.getVehicle(), hoursParked);
        System.out.println(receipt);

        ticket.getSpot().removeVehicle();
        activeTickets.remove(ticket.getTicketNumber());

        return getPricingStrategy(paymentMethod).calculatePrice(ticket.getVehicle(), hoursParked);
    }

    public Ticket getTicket(String ticketNumber) {
        return activeTickets.get(ticketNumber);
    }

    private Payment createPayment(PaymentMethod method) {
        switch (method) {
            case CARD: return new Card("0000000000001234", "Customer", "12/26", "123", new CARDPricing());
            case UPI:  return new Upi(new UPIPricing());
            case CASH: return new Cash(new CASHPricing());
            default: throw new IllegalArgumentException("Unknown payment method: " + method);
        }
    }

    private PricingStrategy getPricingStrategy(PaymentMethod method) {
        switch (method) {
            case CARD: return new CARDPricing();
            case UPI:  return new UPIPricing();
            case CASH: return new CASHPricing();
            default: throw new IllegalArgumentException("Unknown payment method: " + method);
        }
    }
}
