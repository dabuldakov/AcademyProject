package practice.person.exception;

public class PersonIdException extends PersonException{
    public PersonIdException(String message) {
        super("Person id incorrect: " + message);
    }
}
