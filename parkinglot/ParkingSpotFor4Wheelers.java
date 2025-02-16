package parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

public class ParkingSpotFor4Wheelers implements ParkingSpot{


    Boolean isEmpty;

    Map<Integer,String> availableSpot= new HashMap<>();
    List<Integer> takenPlaces = new ArrayList<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String findParkingSpotForVehicle(Vehicle vehicle) {
        takenPlaces.add(pq.peek());
        if(pq.size()==1) {
            setEmpty(false);
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
}
