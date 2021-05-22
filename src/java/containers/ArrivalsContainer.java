package containers;

import pack.Arrival;
import pack.Train;
import pack.TrainStation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrivalsContainer extends MapContainer<Arrival> {

    private final TrainStation parentTrainStation;
    private final int[] capacityArray = new int[1440];

    public ArrivalsContainer(TrainStation parentTrainStation) {
        super();
        this.parentTrainStation = parentTrainStation;
    }

    public boolean createArrival(Train train, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        Arrival newArrival = new Arrival(train, arrivalTime, departureTime);
        if (capacityController(newArrival))
            return this.add(newArrival, newArrival.getId());
        else
            return false;
    }

    /**
     * capacityController() returns TRUE if trainStation is not full
     * and returns FALSE when trainStation is full
     */
    private boolean capacityController(Arrival newArrival) {
        int[] duration = calculateDuration(newArrival);
        for (int i = duration[0]; i <= duration[1]; i++) {
            if (this.parentTrainStation.getMaxCapacity() <= capacityArray[i]) {
                return false;
            }
        }
        for (int i = duration[0]; i <= duration[1]; i++) {
            capacityArray[i]++;
        }
        return true;
    }

    private int[] calculateDuration(Arrival newArrival) {
        LocalDateTime arrivalTime = newArrival.getArrivalTime();
        LocalDateTime departureTime = newArrival.getDepartureTime();
        int start = arrivalTime.getHour() * 60 + arrivalTime.getMinute();
        int stop = departureTime.getHour() * 60 + departureTime.getMinute();
        return new int[]{start, stop};
    }

    @Override
    public Arrival removeById(int wantedId) {
        int[] duration = calculateDuration(map.get(wantedId));
        for (int i = duration[0]; i <= duration[1]; i++) {
            capacityArray[i]--;
        }
        return super.removeById(wantedId);
    }

    public List<Arrival> getProperArrivals(TrainStation from, TrainStation to, LocalDateTime time) {
        Set<Map.Entry<Integer, Arrival>> tmpSet = from.getArrivals().getMap().entrySet();
        List<Map.Entry<Integer, Arrival>> tmpList = new ArrayList<>(tmpSet);
        List<Arrival> finalList = new ArrayList<>();

        for (Map.Entry<Integer, Arrival> arrival : tmpList) {
            if (arrival.getValue().getTrain().getTowns().contains(to.getTownName()) &&
                    arrival.getValue().getDepartureTime().getDayOfYear() == time.getDayOfYear() &&
                    arrival.getValue().getDepartureTime().getHour() > time.getHour()
            ) {
                finalList.add(arrival.getValue());
            }
        }
        return finalList;
    }
}