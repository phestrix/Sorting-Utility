package exception;

public class BadFileException extends RuntimeException {
    public BadFileException(String message) {
        super(message);
    }
}
