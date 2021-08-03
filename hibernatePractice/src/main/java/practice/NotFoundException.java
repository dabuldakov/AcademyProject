package practice;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super("Entity not found. " + message);
    }
}
