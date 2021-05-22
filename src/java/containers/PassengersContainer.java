package containers;

import exceptions.TrainIsFullException;
import pack.Passenger;
import pack.Train;

import java.util.Arrays;

public class PassengersContainer extends MapContainer<Passenger> {

    private final Train parentTrain;
    private final int numberOfSeats;
    private final boolean[] seatsIsFree;

    public PassengersContainer(Train parentTrain, int numberOfSeats) {
        super();
        this.numberOfSeats = numberOfSeats;
        this.parentTrain = parentTrain;
        seatsIsFree = new boolean[numberOfSeats];
        Arrays.fill(seatsIsFree, true);
    }

    /**
     * ticketType=0 - NORMAL,
     * ticketType=1 - REDUCED
     */
    public boolean createPassenger(int ticketType, String firstname, String surname, String email, int phoneNumber) throws TrainIsFullException {
        int seatNumber = seatsManager();
        Passenger newPassenger = new Passenger(
                ticketType,
                firstname,
                surname,
                email,
                phoneNumber,
                seatNumber
        );
        return this.add(newPassenger, newPassenger.getId());
    }

    /**
     * returns first free seat number in train
     * and updates seats[]
     */
    private int seatsManager() throws TrainIsFullException {
        for (int i = 0; i < seatsIsFree.length; i++) {
            if (seatsIsFree[i]) {
                seatsIsFree[i] = false;
                return i + 1;
            }
        }
        throw new TrainIsFullException();
    }

    @Override
    public Passenger removeById(int wantedId) {
        seatsIsFree[wantedId - 1] = true;
        return super.removeById(wantedId);
    }

    public int countFreeSeats() {
        int counter = 0;
        for (boolean seat : seatsIsFree) {
            if (seat) counter++;
        }
        return counter;
    }
}
