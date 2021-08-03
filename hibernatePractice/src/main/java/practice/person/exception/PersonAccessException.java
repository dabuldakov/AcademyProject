package practice.person.exception;

public class PersonAccessException extends PersonException{
    public PersonAccessException() {
        super("Wrong access key.");
    }
}
