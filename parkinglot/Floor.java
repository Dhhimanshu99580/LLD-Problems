package parkinglot;

import java.util.List;

public class Floor {
    private int floorNumber;
    private List<Spot> spots;

    public Floor(int floorNumber, List<Spot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public int getFloorNumber() { return floorNumber; }

    public List<Spot> getSpots() { return spots; }

    public boolean isFloorAvailable(VehicleType vehicleType) {
        SpotType required = spotTypeFor(vehicleType);
        return spots.stream().anyMatch(s -> s.getSpotType() == required && s.isAvailable());
    }

    public Spot getAvailableSpot(VehicleType vehicleType) {
        SpotType required = spotTypeFor(vehicleType);
        return spots.stream()
                .filter(s -> s.getSpotType() == required && s.isAvailable())
                .findFirst()
                .orElse(null);
    }

    private SpotType spotTypeFor(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:  return SpotType.TWO_WHEELERS;
            case CAR:   return SpotType.FOUR_WHEELERS;
            case TRUCK: return SpotType.SIX_WHEELERS;
            default: throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}
