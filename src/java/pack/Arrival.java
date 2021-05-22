package pack;

import java.time.LocalDateTime;
import java.util.Objects;

public class Arrival implements Comparable<Arrival>{

    private final Train train;
    private final LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;
    private static int nextId = 1;
    private final int id;

    public Arrival(Train train, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.train = train;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.id = nextId;
        nextId++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arrival arrival = (Arrival) o;
        return Objects.equals(train, arrival.train) && Objects.equals(arrivalTime, arrival.arrivalTime) && Objects.equals(departureTime, arrival.departureTime);
    }

    public String getStringDepartureTime() {
        return departureTime.getHour() + " " + departureTime.getMinute();
    }

    @Override
    public int hashCode() {
        return Objects.hash(train, arrivalTime, departureTime);
    }

    @Override
    public int compareTo(Arrival o) {
        return this.getDepartureTime().compareTo(o.departureTime);
    }

    public Train getTrain() {
        return train;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Arrival.nextId = nextId;
    }

    public int getId() {
        return id;
    }
}
