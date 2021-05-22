package pack;

import java.util.Objects;

public class Passenger {

    private TicketType ticketType;
    private String firstname;
    private String surname;
    private String email;
    private int seatNumber;
    private int phoneNumber;
    private static int nextId = 1;
    private final int id;

    public Passenger(
            int ticketType,
            String firstname,
            String surname,
            String email,
            int phoneNumber,
            int seatNumber
            ) {

        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.seatNumber = seatNumber;
        this.id = nextId;
        nextId++;

        if(ticketType == 0) this.ticketType = TicketType.NORMAL;
        if(ticketType == 1) this.ticketType = TicketType.REDUCED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return phoneNumber == passenger.phoneNumber && ticketType == passenger.ticketType && Objects.equals(firstname, passenger.firstname) && Objects.equals(surname, passenger.surname) && Objects.equals(email, passenger.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketType, firstname, surname, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "java.pack.Passenger{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void setNextId(int nextId) {
        Passenger.nextId = nextId;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
