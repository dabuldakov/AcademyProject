package practice.person.exception;

public class PersonNotFoundException extends PersonException{
    public PersonNotFoundException(String message) {
        super("Person not found. " + message);
    }
}
