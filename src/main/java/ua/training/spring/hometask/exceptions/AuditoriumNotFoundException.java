package ua.training.spring.hometask.exceptions;

public class AuditoriumNotFoundException extends RuntimeException {


    public AuditoriumNotFoundException(String message) {
        super(message);
    }
}
