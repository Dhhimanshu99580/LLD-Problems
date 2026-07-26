package parkinglot;

public class Spot {
    private int spotNumber;
    private SpotType spotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public Spot(int spotNumber, SpotType spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public int getSpotNumber() { return spotNumber; }

    public SpotType getSpotType() { return spotType; }

    public boolean isAvailable() { return !isOccupied; }

    public Vehicle getParkedVehicle() { return parkedVehicle; }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }
}
