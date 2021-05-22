package exceptions;

public class InvalidDataException extends Exception{
    @Override
    public String getMessage() {
        return "Wprowadzono nieprawidłowe dane!";
    }
}
