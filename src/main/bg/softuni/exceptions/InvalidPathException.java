package main.bg.softuni.exceptions;

public class InvalidPathException extends RuntimeException {

    public static final String INVALID_PATH = "The source does not exists.";

    public InvalidPathException() {
        super(INVALID_PATH);
    }

    public InvalidPathException(String message) {
        super(message);
    }
}
