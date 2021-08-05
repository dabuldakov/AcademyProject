package practice.exception;

public class IdException extends RuntimeException {
    public IdException(String message) {
        super("Id incorrect: " + message);
    }
}
