package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class TableEntry {

    //values used in trainTable
    private final String trainName;
    private final String departureDateTime;
    private final String ticketPrice;
    private final Button buyBtn;

    public TableEntry(String trainName, LocalDateTime departureDateTime, String ticketPrice, int trainId) {
        this.trainName = trainName;
        this.departureDateTime = departureDateTime.getHour() + ":" + departureDateTime.getMinute();
        this.ticketPrice = ticketPrice;
        this.buyBtn = new Button("Kup bilet");
        this.buyBtn.setOnAction(e -> {
            String message = "Czy na pewno chcesz kupić bilet na pociąg " + trainName + "?";
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.YES)
                buyTicketWindow(departureDateTime, ticketPrice, trainId);
        });
    }

    private void buyTicketWindow(LocalDateTime departureDateTime, String ticketPrice, int trainId) {

        //load fxml file
        Parent parent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buyTicket.fxml"));
        loader.setControllerFactory(c -> new BuyTicketController(departureDateTime, ticketPrice, trainId));
        try {
            parent = loader.load();
        } catch (Exception ex) {
            System.err.println("Błąd wczytanie buyTicket.fxml");
            System.err.println(ex.getMessage());
            return;
        }

        //initialize stage
        Stage buyTicketStage = new Stage();
        buyTicketStage.setTitle("Kupowanie biletu");
        buyTicketStage.setScene(new Scene(parent));
        buyTicketStage.setResizable(false);
        buyTicketStage.show();
    }

    public String getTrainName() {
        return trainName;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public Button getBuyBtn() {
        return buyBtn;
    }
}