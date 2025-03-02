package parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

public class ParkingSpotFor2Wheelers implements ParkingSpot{
    boolean isEmpty = false;
    int price;
    Map<Integer,String> availableSpot= new HashMap<>();
    List<Integer> takenPlaces = new ArrayList<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String findParkingSpotForVehicle(Vehicle vehicle) {
        if(pq.isEmpty())  {
            System.out.println("queue is empty");
            return "NA";
        }
        takenPlaces.add(pq.peek());
        if(pq.size()==1) {
            setEmpty(true);
        }
        return availableSpot.get(pq.poll());
    }

    @Override
    public void initalizeDistance() {
        for(int i=1;i<100;i++) {
            pq.add(i);
            availableSpot.put(i, UUID.randomUUID().toString().replaceAll("[^a-zA-Z]", "").substring(0, 5));
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
