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
    public InsideButton(Floor floor) {
        this.floor = floor;
    }
    @Override
    public void pressButton() {
        System.out.println("Inside button pressed for floor: " + floor);
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
        System.out.println("Elevator is idle. Handling request to floor: " + request.getFloor().getCurrentFloor());
        // Logic to move the elevator to the requested floor
    }
}

class MovingUpState implements ElevatorState {
    @Override
    public void handleRequest(Elevator elevator, InternalRequest request) {
        System.out.println("Elevator is moving. Handling request to floor: " + request.getFloor().getCurrentFloor());
        // Logic to queue the request or handle it based on the current direction
    }
}

class MovingDownState implements ElevatorState {
    @Override
    public void handleRequest(Elevator elevator, InternalRequest request) {
        System.out.println("Elevator is moving down. Handling request to floor: " + request.getFloor().getCurrentFloor());
        // Logic to queue the request or handle it based on the current direction
    }
}

class ElevatorStrategyImpl implements ElevatorStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, ExternalRequest externalRequest) {
        // Logic to select the best elevator based on the request
        // For simplicity, we can return the first idle elevator
        for (Elevator elevator : elevators) {
            if (elevator.getElevatorStatus() == ElevatorStatus.IDLE) {
                return elevator;
            }
        }
        // If no idle elevator is found, return null or implement a more complex selection logic
        return null;
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
        this.currentFloor = new Floor(0);
        this.elevatorStatus = ElevatorStatus.IDLE;
        this.currentCapacity = capacity;
        this.buttons = new ArrayList<>();
        this.insideDisplays = new InsideDisplay();
        this.door = new Door();
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

}

public class Main {
    public static void main(String[] args) {

    }
}
