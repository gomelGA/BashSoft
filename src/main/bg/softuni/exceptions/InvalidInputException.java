package main.bg.softuni.exceptions;

public class InvalidInputException extends RuntimeException {

    private static final String INVALID_INPUT = "The command '%s' is invalid";

    public InvalidInputException(String message) {
        super(String.format(INVALID_INPUT, message));
    }
}
