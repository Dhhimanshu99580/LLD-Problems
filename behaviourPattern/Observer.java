package behaviourPattern;

import java.util.ArrayList;

public interface Observer {
    public void update(String message);
}

public class EmailObserver implements Observer {
    @Override
    public void update(String message) {
        // logic to send email notification
    }
}

public class SmsObserver implements Observer {
    @Override
    public void update(String message) {
        // logic to send sms notification
    }
}

// Interface for event source
public interface Source{
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserver(String message);
    public void processSettlementEvent(String event);
}

public class SettlementEventSource implements Source {
    private List<Observer> observers = new ArrayList<>();
    public SettlementEventSource() {
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String message) {
        for(Observer observer : observers) {
            observer.update(message);
        }
    }
    @Override
    public void processSettlementEvent(String event) {
        // logic to process settlement event
        // after processing the event, notify the observers
        notifyObserver(event);
    }
}

public class Client {
    public static void main(String[] args) {
        SettlementEventSource settlementEventSource = new SettlementEventSource();
        EmailObserver emailObserver = new EmailObserver();
        SmsObserver smsObserver = new SmsObserver();
        settlementEventSource.addObserver(emailObserver);
        settlementEventSource.addObserver(smsObserver);
        settlementEventSource.processSettlementEvent("Settlement event processed");
    }
}
