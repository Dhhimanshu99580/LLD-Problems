package elevatorsystem;
import java.time.LocalDateTime;
import java.util.*;


enum ElevatorStatus { IDLE, MOVING_UP, MOVING_DOWN }
enum Direction { UP, DOWN, NONE }
enum DoorStatus { OPEN, CLOSED }
enum RequestType { INTERNAL, EXTERNAL }

interface Button {
   void pressButton();
}
interface Display {
    void showMessage(String message);
    void clearMessage();

}
interface ElevatorStrategy {
  public Elevator selectElevator(List<Elevator>elevators, ExternalRequest externalRequest);
}

interface ElevatorState {
 void handleRequest(Elevator elevator, InternalRequest request);
}

class Door {
    DoorStatus doorStatus;
    public void openDoor() {
        doorStatus = DoorStatus.OPEN;
    }
    public void closeDoor() {
        doorStatus = DoorStatus.CLOSED;
    }
    public boolean isOpen() {
        return doorStatus == DoorStatus.OPEN;
    }
}

class OutSideDisplay implements Display {
    String message;

    @Override
    public void showMessage(String message) {
        this.message = message;
        System.out.println("Outside display shows: " + message);
    }

    @Override
    public void clearMessage() {
        this.message = "";
        System.out.println("Outside display cleared");
    }
}
class InsideDisplay implements Display {
    String message;

    @Override
    public void showMessage(String message) {
        this.message = message;
        System.out.println("Inside display shows: " + message);
    }

    @Override
    public void clearMessage() {
        this.message = "";
        System.out.println("Inside display cleared");
    }
}

class OutsideButton implements Button {
    Direction direction;
    public OutsideButton(Direction direction) {
        this.direction = direction;
    }
    @Override
    public void pressButton() {
        System.out.println("Outside button pressed for direction: " + direction);
    }
}


class InsideButton implements Button {
    Floor floor;
    Elevator elevator;
    public InsideButton(Floor floor) {
        this.floor = floor;
    }
    @Override
    public void pressButton() {
        System.out.println("Inside button pressed for floor: " + floor);
        InternalRequest request = new InternalRequest(this, floor);
        elevator.handleRequest(request);
    }
}

class DoorButton implements Button {
    Door door;
    public DoorButton(Door door) {
        this.door = door;
    }
    @Override
    public void pressButton() {
        if (door.isOpen()) {
            door.closeDoor();
            System.out.println("Door closed");
        } else {
            door.openDoor();
            System.out.println("Door opened");
        }
    }
}
class Floor {
    List<Door>doors;
    List<OutsideButton> outsideButtons;
    List<OutSideDisplay> outSideDisplays;
    int floor;
    Floor(int floor) {
        this.floor = floor;
        doors = new ArrayList<>();
        outsideButtons = new ArrayList<>();
        outSideDisplays = new ArrayList<>();
    }
    public int getCurrentFloor() {
        return floor;
    }
}

class ExternalRequest {
    Button outsideButton;
    Floor floor;
    LocalDateTime requestTime;
    ExternalRequest(Button outsideButton, Floor floor) {
        this.outsideButton = outsideButton;
        this.floor = floor;
        this.requestTime = LocalDateTime.now();
    }
    public Floor getFloor() {
        return floor;
    }
    public Button getOutsideButton() {
        return outsideButton;
    }
    public LocalDateTime getRequestTime() {
        return requestTime;
    }
}

class InternalRequest {
    Button button;
    Floor floor;
    LocalDateTime requestTime;

    InternalRequest(Button button, Floor floor) {
        this.button = button;
        this.floor = floor;
        this.requestTime = LocalDateTime.now();
    }

    public Floor getFloor() {
        return floor;
    }

    public Button getButton() {
        return button;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }
}

class IdleState implements ElevatorState {
    @Override
    public void handleRequest(Elevator elevator, InternalRequest request) {
        int target = request.getFloor().getCurrentFloor();
        System.out.println("Elevator " + elevator.getElevatorId() + " is idle. Handling request to floor: " + target);
        elevator.addRequest(target);
    }
}

class MovingUpState implements ElevatorState {
    @Override
    public void handleRequest(Elevator elevator, InternalRequest request) {
        int target = request.getFloor().getCurrentFloor();
        System.out.println("Elevator " + elevator.getElevatorId() + " is moving up. Queuing request to floor: " + target);
        elevator.addRequest(target);
    }
}

class MovingDownState implements ElevatorState {
    @Override
    public void handleRequest(Elevator elevator, InternalRequest request) {
        int target = request.getFloor().getCurrentFloor();
        System.out.println("Elevator " + elevator.getElevatorId() + " is moving down. Queuing request to floor: " + target);
        elevator.addRequest(target);
    }
}

class LookElevatorStrategyImpl implements ElevatorStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, ExternalRequest externalRequest) {
        Map<Elevator,Integer> elevatorDistances = new HashMap<>();
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorStatus() == ElevatorStatus.IDLE) {
                int distance = calculateDistance(elevator.currentFloor.getCurrentFloor(), externalRequest.getFloor().getCurrentFloor());
                elevatorDistances.put(elevator, distance);
            } else if(elevator.getElevatorDirection().equals(Direction.UP) && externalRequest.getFloor().getCurrentFloor()> elevator.currentFloor.getCurrentFloor()){
                int distance = calculateDistance(elevator.currentFloor.getCurrentFloor(), externalRequest.getFloor().getCurrentFloor());
                elevatorDistances.put(elevator, distance);
            } else if(elevator.getElevatorDirection().equals(Direction.DOWN) && externalRequest.getFloor().getCurrentFloor()< elevator.currentFloor.getCurrentFloor()) {
                int distance = calculateDistance(elevator.currentFloor.getCurrentFloor(), externalRequest.getFloor().getCurrentFloor());
                elevatorDistances.put(elevator, distance);
            }
        }
        return elevatorDistances.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
    private int calculateDistance(int elevatorFloor, int requestFloor) {
        return Math.abs(elevatorFloor - requestFloor);
    }
}

class Elevator {
    int elevatorId;

    Door door;
    List<InsideButton> buttons;
    InsideDisplay insideDisplays;
    ElevatorStatus elevatorStatus;
    int currentCapacity;
    Floor currentFloor;
    PriorityQueue<Integer>upQueue;
    PriorityQueue<Integer> downQueue;
    Elevator(int elevatorId, int capacity) {
        this.elevatorId = elevatorId;
        this.currentFloor = new Floor(0);
        this.elevatorStatus = ElevatorStatus.IDLE;
        this.currentCapacity = capacity;
        this.buttons = new ArrayList<>();
        this.insideDisplays = new InsideDisplay();
        this.door = new Door();
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>(Collections.reverseOrder());
    }
    void handleRequest(InternalRequest request) {
        switch(elevatorStatus) {
            case IDLE:
                new IdleState().handleRequest(this, request);
                break;
            case MOVING_UP:
                new MovingUpState().handleRequest(this, request);
                break;
            case MOVING_DOWN:
                new MovingDownState().handleRequest(this, request);
                break;
        }
        // Logic to handle the internal request based on the current state
        // For example, if the elevator is idle, it can move to the requested floor
        // If it's moving, it can queue the request or handle it based on the direction
    }
    void moveUp() {
        elevatorStatus = ElevatorStatus.MOVING_UP;
        // Logic to move the elevator up
    }

    void moveDown() {
        elevatorStatus = ElevatorStatus.MOVING_DOWN;
        // Logic to move the elevator down
    }
    void openDoor() {
        door.openDoor();
    }
    void closeDoor() {
        door.closeDoor();
    }
    void addRequest(int floor) {
        if (floor > currentFloor.getCurrentFloor()) {
            upQueue.add(floor);
        } else {
            downQueue.add(floor);
        }
    }
    Direction getElevatorDirection() {
        if (elevatorStatus == ElevatorStatus.MOVING_UP) {
            return Direction.UP;
        } else if (elevatorStatus == ElevatorStatus.MOVING_DOWN) {
            return Direction.DOWN;
        } else {
            return Direction.NONE;
        }
    }
    ElevatorStatus getElevatorStatus() {
        return elevatorStatus;
    }
    int getElevatorId() {
        return elevatorId;
    }

    public void processQueue() {
        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            if (!upQueue.isEmpty()) {
                int nextFloor = upQueue.poll();
                System.out.println("Elevator " + elevatorId + " moving up to floor " + nextFloor);
                currentFloor = new Floor(nextFloor);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (!downQueue.isEmpty()) {
                int nextFloor = downQueue.poll();
                System.out.println("Elevator " + elevatorId + " moving down to floor " + nextFloor);
                currentFloor = new Floor(nextFloor);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        elevatorStatus = ElevatorStatus.IDLE;
        System.out.println("Elevator " + elevatorId + " is now idle at floor " + currentFloor.getCurrentFloor());
    }
}
class ElevatorManger {
    List<Elevator> elevators;
    ElevatorStrategy elevatorStrategy;
    List<ExternalRequest> externalRequests;
    static ElevatorManger instance;
    ElevatorManger() {
        elevators = new ArrayList<>();
        externalRequests = new ArrayList<>();
        elevatorStrategy = new LookElevatorStrategyImpl();
    }

    static ElevatorManger getInstance() {
        if (instance == null) {
            instance = new ElevatorManger();
        }
        return instance;
    }
    public void processExternalRequest(ExternalRequest externalRequest) {
        Elevator selectedElevator = elevatorStrategy.selectElevator(elevators, externalRequest);
        if (selectedElevator != null) {
            selectedElevator.addRequest(externalRequest.getFloor().getCurrentFloor());
            System.out.println("External request processed by Elevator ID: " + selectedElevator.getElevatorId());
            selectedElevator.processQueue();
        } else {
            System.out.println("No suitable elevator found for the request.");
        }
    }
}

class Building {
    List<Floor> floors;
    List<Elevator> elevators;
    ElevatorManger elevatorManger;
    Building(int numberOfFloors, int numberOfElevators, int elevatorCapacity) {
        floors = new ArrayList<>();
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i++) {
            floors.add(new Floor(i));
        }
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i, elevatorCapacity));
        }
        elevatorManger = ElevatorManger.getInstance();
        elevatorManger.elevators = elevators;
    }
    public int getTotalFloors() {
        return floors.size();
    }
    public int getTotalElevators() {
        return elevators.size();
    }
    public ElevatorManger getElevatorManger() {
        return elevatorManger;
    }

}

public class Main {
    public static void main(String[] args) {
        Building building = new Building(10, 3, 5);
        ElevatorManger manager = building.getElevatorManger();

        System.out.println("=== Scenario 1: External request — person on floor 5 pressing UP ===");
        Floor floor5 = building.floors.get(5);
        OutsideButton upButton = new OutsideButton(Direction.UP);
        upButton.pressButton();
        ExternalRequest req1 = new ExternalRequest(upButton, floor5);
        manager.processExternalRequest(req1);

        System.out.println("\n=== Scenario 2: External request — person on floor 8 pressing UP ===");
        Floor floor8 = building.floors.get(8);
        OutsideButton upButton2 = new OutsideButton(Direction.UP);
        upButton2.pressButton();
        ExternalRequest req2 = new ExternalRequest(upButton2, floor8);
        manager.processExternalRequest(req2);

        System.out.println("\n=== Scenario 3: Internal request — inside elevator 0, press floor 3 (going down) ===");
        Elevator elevator0 = building.elevators.get(0);
        System.out.println("Elevator 0 currently at floor: " + elevator0.currentFloor.getCurrentFloor());
        Floor floor3 = building.floors.get(3);
        InternalRequest internalReq = new InternalRequest(null, floor3);
        elevator0.handleRequest(internalReq);
        elevator0.processQueue();
    }
}
