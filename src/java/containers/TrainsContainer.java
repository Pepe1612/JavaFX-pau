package containers;

import pack.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrainsContainer extends MapContainer<Train> {

    public TrainsContainer() {
        super();
    }

    public boolean createTrain(String name, int numberOfSeats, ArrayList<String> towns) {
        Train newTrain = new Train(name, numberOfSeats, towns);
        return this.add(newTrain, newTrain.getId());
    }

    public List<Train> getList() {
        Set<Map.Entry<Integer, Train>> tmpSet = this.map.entrySet();
        List<Train> finalList = new ArrayList<>();
        for (Map.Entry<Integer, Train> element: tmpSet) {
            finalList.add(element.getValue());
        }
        return finalList;
    }

//    public List<Train> getProperTrains(TrainStation from, TrainStation to, LocalDateTime time) //{
//
//        //from.getArrivals().contains()
//
////        Set<Map.Entry<Integer, Arrival>> tmpSet = from.getArrivals().getMap().entrySet();
////        List<Map.Entry<Integer, Arrival>> tmpArray = new ArrayList<>(tmpSet);
////        List<Train> finalList = new ArrayList<>();
////
////        for (Map.Entry<Integer, Arrival> arrival: tmpArray) {
////            if(arrival.getValue().)
////        }
//
////        Set<Map.Entry<Integer, Train>> tmpSet = map.entrySet();
////        List<Map.Entry<Integer, Train>> tmpArray = new ArrayList<>(tmpSet);
////        List<Train> finalList = new ArrayList<>();
////        for (Map.Entry<Integer, Train> train : tmpArray) {
////            if (train.getValue().getTowns().contains(from.getTownName())
////                    && train.getValue().getTowns().contains(to.getTownName()
////                    && from.getArrivals().getMap().)
////            ) {
////                finalList.add(train.getValue());
////            }
////        }
//
//        //return finalList;
//    //}
}