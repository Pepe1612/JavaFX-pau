package sample;

import containers.TrainStationsContainer;
import containers.TrainsContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    public static TrainsContainer trainsContainer;
    public static TrainStationsContainer trainStationsContainer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("trainFinder.fxml"));
        loader.setControllerFactory(c -> new TrainFinderController(trainsContainer, trainStationsContainer));
        Parent root = loader.load();
        primaryStage.setTitle("PKP - wyszukiwarka połączeń");
        primaryStage.setScene(new Scene(root, 900, 550));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.initializeContainers();
        launch(args);
    }

    private void initializeContainers() {
        trainsContainer = new TrainsContainer();
        trainStationsContainer = new TrainStationsContainer();

        ArrayList<String> towns = new ArrayList<>(Arrays.asList("Gdańsk", "Warszawa", "Kraków", "Rzeszów"));

        //TRAINS
        trainsContainer.createTrain("Hetman", 100, towns);
        trainsContainer.createTrain("Lubomirski", 2, towns);
        trainsContainer.createTrain("Pendolino", 200, towns);
        trainsContainer.createTrain("Express", 300, towns);
        trainsContainer.createTrain("Cracovia", 50, towns);

        //TRAIN STATIONS
        trainStationsContainer.createTrainStation("Gdańsk", 15);
        trainStationsContainer.createTrainStation("Warszawa", 30);
        trainStationsContainer.createTrainStation("Kraków", 20);
        trainStationsContainer.createTrainStation("Rzeszów", 5);

        //ARRIVALS ID1
        trainStationsContainer.getById(1).getArrivals().createArrival(
                trainsContainer.getById(1),
                LocalDateTime.of(2021, 5, 25, 16, 21),
                LocalDateTime.of(2021, 5, 25, 16, 42)
        );
        trainStationsContainer.getById(1).getArrivals().createArrival(
                trainsContainer.getById(2),
                LocalDateTime.of(2021, 5, 25, 18, 2),
                LocalDateTime.of(2021, 5, 25, 18, 30)
        );

        //ARRIVALS ID2
        trainStationsContainer.getById(2).getArrivals().createArrival(
                trainsContainer.getById(1),
                LocalDateTime.of(2021, 5, 25, 13, 21),
                LocalDateTime.of(2021, 5, 25, 13, 33)
        );
        trainStationsContainer.getById(2).getArrivals().createArrival(
                trainsContainer.getById(2),
                LocalDateTime.of(2021, 5, 25, 18, 2),
                LocalDateTime.of(2021, 5, 25, 18, 23)
        );
        trainStationsContainer.getById(2).getArrivals().createArrival(
                trainsContainer.getById(3),
                LocalDateTime.of(2021, 5, 25, 9, 55),
                LocalDateTime.of(2021, 5, 25, 10, 15)
        );
        trainStationsContainer.getById(2).getArrivals().createArrival(
                trainsContainer.getById(4),
                LocalDateTime.of(2021, 5, 25, 22, 41),
                LocalDateTime.of(2021, 5, 25, 22, 58)
        );

        //ARRIVALS ID3
        trainStationsContainer.getById(3).getArrivals().createArrival(
                trainsContainer.getById(1),
                LocalDateTime.of(2021, 5, 25, 13, 21),
                LocalDateTime.of(2021, 5, 25, 13, 33)
        );
        trainStationsContainer.getById(3).getArrivals().createArrival(
                trainsContainer.getById(2),
                LocalDateTime.of(2021, 5, 25, 18, 2),
                LocalDateTime.of(2021, 5, 25, 18, 23)
        );
        trainStationsContainer.getById(3).getArrivals().createArrival(
                trainsContainer.getById(3),
                LocalDateTime.of(2021, 5, 25, 9, 55),
                LocalDateTime.of(2021, 5, 25, 10, 15)
        );
        trainStationsContainer.getById(3).getArrivals().createArrival(
                trainsContainer.getById(4),
                LocalDateTime.of(2021, 5, 25, 22, 41),
                LocalDateTime.of(2021, 5, 25, 22, 58)
        );

        //ARRIVALS ID4
        trainStationsContainer.getById(4).getArrivals().createArrival(
                trainsContainer.getById(1),
                LocalDateTime.of(2021, 5, 25, 13, 21),
                LocalDateTime.of(2021, 5, 25, 13, 33)
        );
        trainStationsContainer.getById(4).getArrivals().createArrival(
                trainsContainer.getById(4),
                LocalDateTime.of(2021, 5, 25, 22, 41),
                LocalDateTime.of(2021, 5, 25, 22, 58)
        );
    }
}
