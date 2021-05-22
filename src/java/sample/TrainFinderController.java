package sample;

import containers.TrainStationsContainer;
import containers.TrainsContainer;
import pack.Arrival;
import pack.TrainStation;
import exceptions.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrainFinderController {

    TrainsContainer trainsContainer;
    TrainStationsContainer trainStationsContainer;

    public TrainFinderController(TrainsContainer trainsContainer, TrainStationsContainer trainStationsContainer) {
        this.trainsContainer = trainsContainer;
        this.trainStationsContainer = trainStationsContainer;
    }

    @FXML
    private TableView<TableEntry> trainsTable;
    @FXML
    private ComboBox<TrainStation> fromCombo;
    @FXML
    private ComboBox<TrainStation> toCombo;
    @FXML
    private DatePicker departureDataPicker;
    @FXML
    private ComboBox<String> departureTimeCombo;
    @FXML
    private TableColumn<TableEntry, String> trainNameColumn;
    @FXML
    private TableColumn<TableEntry, String> departureTimeColumn;
    @FXML
    private TableColumn<TableEntry, String> ticketPriceColumn;
    @FXML
    private TableColumn<TableEntry, Button> buttonsColumn;

    @FXML
    public void initialize() {

        fromCombo.setItems(FXCollections.observableList(trainStationsContainer.getList()));
        toCombo.setItems(FXCollections.observableList(trainStationsContainer.getList()));
        departureTimeCombo.setItems(FXCollections.observableArrayList("-dowolna-", "00:00", "04:00", "08:00", "12:00", "16:00", "20:00"));
    }

    public void setTrainsTable() {

        TrainStation firstStation;
        TrainStation secondStation;
        LocalDate departureDate;
        String tmpDepartureTime;

        //get and validate data from user
        try {
            firstStation = this.fromCombo.getValue();
            if (firstStation == null) throw new InvalidDataException();

            secondStation = this.toCombo.getValue();
            if (secondStation == null) throw new InvalidDataException();

            if (firstStation.equals(secondStation)) throw new SameTrainStationException();

            departureDate = this.departureDataPicker.getValue();
            if (departureDate == null) throw new InvalidDataException();

            tmpDepartureTime = this.departureTimeCombo.getValue();
            if (tmpDepartureTime == null) throw new InvalidDataException();

        } catch (Exception ex) {
            Alert warning = new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK);
            warning.showAndWait();
            return;
        }

        LocalTime departureTime = null;

        switch (tmpDepartureTime) {
            case "-dowolna-", "00:00" -> departureTime = LocalTime.of(0, 0);
            case "04:00" -> departureTime = LocalTime.of(4, 0);
            case "08:00" -> departureTime = LocalTime.of(8, 0);
            case "12:00" -> departureTime = LocalTime.of(12, 0);
            case "16:00" -> departureTime = LocalTime.of(16, 0);
            case "20:00" -> departureTime = LocalTime.of(20, 0);
        }

        assert departureTime != null;
        LocalDateTime time = LocalDateTime.of(departureDate, departureTime);

        List<Arrival> tmpList = firstStation.getArrivals().getProperArrivals(firstStation, secondStation, time);
        List<TableEntry> tableEntryList = new ArrayList<>();

        for (Arrival arrival : tmpList) {
            tableEntryList.add(new TableEntry(arrival.getTrain().getName(), arrival.getDepartureTime(), "30", arrival.getTrain().getId()));
        }

        ObservableList<TableEntry> arrivalsObservableList = FXCollections.observableArrayList(tableEntryList);

        trainNameColumn.setCellValueFactory(new PropertyValueFactory<>("trainName"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureDateTime"));
        ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        buttonsColumn.setCellValueFactory(new PropertyValueFactory<>("buyBtn"));

        trainsTable.setItems(arrivalsObservableList);

        trainsTable.setRowFactory(c -> {
            TableRow<TableEntry> tableRow = new TableRow<>();
            tableRow.setOnMouseEntered(e -> {
                Tooltip tooltip;
                try {
                    tooltip = new Tooltip(
                            "Pociąg:" + tableRow.getItem().getTrainName() +
                            " Godzina:" + tableRow.getItem().getDepartureDateTime() +
                            " Cena:" + tableRow.getItem().getTicketPrice()
                    );
                } catch (Exception ex) {
                    return;
                }
                tableRow.setTooltip(tooltip);
            });
            return tableRow;
        });
    }
}
