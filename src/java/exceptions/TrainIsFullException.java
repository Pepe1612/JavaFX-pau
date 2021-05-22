package exceptions;

public class TrainIsFullException extends Exception {
    @Override
    public String getMessage() {
        return "Train Is Full!";
    }
}