package Gruppe3.Webseite.application.exception;

public final class InvalidEvent extends Exception {
    /**
     * Creates a InvalidEvent exception with given String.
     *
     * @param exc String to create exception with
     */
    public InvalidEvent(final String exc) {
        super(exc);
    }

    /**
     * Get the exception message.
     *
     * @return The exception message.
     */
    public String getMessage() {
        return super.getMessage();
    }
}