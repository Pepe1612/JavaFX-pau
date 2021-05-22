package pack;

import containers.PassengersContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Train {

    private String name;
    private PassengersContainer passengers;
    private Delay delay;
    private int numberOfSeats;
    private List<String> towns;
    private static int nextId = 1;
    private int id;

    public Train(String name, int numberOfSeats, List<String> towns) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.passengers = new PassengersContainer(this, numberOfSeats);
        this.delay = Delay.ONTIME;
        this.id = nextId;
        nextId++;

        this.towns = towns;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return numberOfSeats == train.numberOfSeats && Objects.equals(name, train.name) && delay == train.delay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public PassengersContainer getPassengers() {
        return passengers;
    }

    public Delay getDelay() {
        return delay;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public List<String> getTowns() {
        return towns;
    }

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassengers(PassengersContainer passengers) {
        this.passengers = passengers;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public static void setNextId(int nextId) {
        Train.nextId = nextId;
    }

    public void setId(int id) {
        this.id = id;
    }
}

enum Delay {
    DELAYED {
        @Override
        public String toString() {
            return "Delayed";
        }
    },
    ONTIME {
        @Override
        public String toString() {
            return "On time";
        }
    }
}