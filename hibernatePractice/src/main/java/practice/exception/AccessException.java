package practice.exception;

public class AccessException extends RuntimeException{
    public AccessException() {
        super("Wrong access key.");
    }
}
