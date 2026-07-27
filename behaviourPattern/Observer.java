package behaviourPattern;

import java.util.ArrayList;
import java.util.List;

/********* Observer Pattern **********/

interface Observer {
    void update(String message);
}

class EmailObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Email notification: " + message);
    }
}

class SmsObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("SMS notification: " + message);
    }
}

interface Source {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver(String message);
    void processSettlementEvent(String event);
}

class SettlementEventSource implements Source {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public void processSettlementEvent(String event) {
        // process the event, then notify all observers
        notifyObserver(event);
    }
}

class ObserverClient {
    public static void main(String[] args) {
        SettlementEventSource source = new SettlementEventSource();
        source.addObserver(new EmailObserver());
        source.addObserver(new SmsObserver());
        source.processSettlementEvent("Settlement event processed");
    }
}
