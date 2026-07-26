package parkinglot;

public interface Payment {
    String process(Vehicle vehicle, int hoursParked);
}
