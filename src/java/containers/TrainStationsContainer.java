package containers;

import pack.TrainStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrainStationsContainer extends MapContainer<TrainStation> {

    public TrainStationsContainer() {
        super();
    }

    public boolean createTrainStation(String townName, int maxCapacity) {
        TrainStation newTrainStation = new TrainStation(townName, maxCapacity);
        return this.add(newTrainStation, newTrainStation.getId());
    }

    public List<TrainStation> getList() {
        Set<Map.Entry<Integer, TrainStation>> tmpSet = this.map.entrySet();
        List<TrainStation> finalList = new ArrayList<>();
        for (Map.Entry<Integer, TrainStation> element : tmpSet) {
            finalList.add(element.getValue());
        }
        return finalList;
    }
}