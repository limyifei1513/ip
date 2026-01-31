package fei;

/**
 * Represents an exception specific to the Fei task manager application.
 */
public class FeiException extends Exception {

    /**
     * Creates a FeiException with the specified error message.
     *
     * @param message Error message describing the cause of the exception.
     */
    public FeiException(String message) {
        super(message);
    }
}
