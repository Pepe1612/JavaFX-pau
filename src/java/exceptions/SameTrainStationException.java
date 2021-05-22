package exceptions;

public class SameTrainStationException extends Exception{
    @Override
    public String getMessage() {
        return "Wybrane stacje początkowa i końcowa są takie same!";
    }
}
