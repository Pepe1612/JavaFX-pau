package sample;

import exceptions.InvalidDataException;
import exceptions.TrainIsFullException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pack.TicketType;

import java.time.LocalDateTime;

public class BuyTicketController {

    private final LocalDateTime departureDateTime;
    private final String ticketPrice;
    private final int trainId;

    public BuyTicketController(LocalDateTime departureDateTime, String ticketPrice, int trainId) {
        this.departureDateTime = departureDateTime;
        this.ticketPrice = ticketPrice;
        this.trainId = trainId;
    }

    @FXML
    private Label trainNameLabel;
    @FXML
    private Label departureDateLabel;
    @FXML
    private Label departureTimeLabel;
    @FXML
    private Label ticketPriceLabel;
    @FXML
    private Label seatsLabel;

    @FXML
    private TextField firstNameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField phoneNumberText;
    @FXML
    private CheckBox reducedTicketCheckBox;

    public void initialize() {
        //train information
        this.trainNameLabel.setText(Main.trainsContainer.getById(trainId).getName());
        String month = Integer.toString(departureDateTime.getMonthValue());
        if (departureDateTime.getMonthValue() < 10) month = "0" + departureDateTime.getMonthValue();
        this.departureDateLabel.setText(departureDateTime.getDayOfMonth() + "." + month + "." + departureDateTime.getYear());
        this.departureTimeLabel.setText(departureDateTime.getHour() + ":" + departureDateTime.getMinute());
        this.ticketPriceLabel.setText(ticketPrice + "zł");
        this.seatsLabel.setText(Integer.toString(Main.trainsContainer.getById(trainId).getPassengers().countFreeSeats()));
    }

    @FXML
    private void buyTicketAction() {
        String firstName;
        String surname;
        String email;
        int phoneNumber;
        TicketType ticketType;

        try {
            firstName = firstNameText.getText();
            if(firstName.equals("")) throw new InvalidDataException();

            surname = surnameText.getText();
            if(surname.equals("")) throw new InvalidDataException();

            email = emailText.getText();
            if(email.equals("")) throw new InvalidDataException();

            phoneNumber = Integer.parseInt(phoneNumberText.getText());
            if(phoneNumber == 0) throw new InvalidDataException();

            if(reducedTicketCheckBox.isSelected()) ticketType = TicketType.REDUCED;
            else ticketType = TicketType.NORMAL;

        } catch (Exception ex) {
            Alert warning = new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK);
            warning.showAndWait();
            return;
        }

        try{
            Main.trainsContainer.getById(trainId).getPassengers().createPassenger(
                    ticketType.ordinal(),
                    firstName,
                    surname,
                    email,
                    phoneNumber
            );
        } catch (TrainIsFullException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
            return;
        }
        initialize();
    }

    @FXML
    private void showTooltip() {
        Tooltip tooltip = new Tooltip();
    }
}
