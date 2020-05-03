package be.khleuven.vgo.domain;

public class DomainException extends Exception {
    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Exception exception) {
        super(message, exception);
    }
}
