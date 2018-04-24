package pl.edu.agh.wiet.studiesplanner.gui.exceptions;

public class EmptyValueException extends RuntimeException {

    public EmptyValueException() {
    }

    public EmptyValueException(String message) {
        super(message);
    }

    public EmptyValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyValueException(Throwable cause) {
        super(cause);
    }

    public EmptyValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
