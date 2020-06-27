package Gruppe3.Webseite.application.exception;

public final class InvalidVote extends Exception {
    /**
     * Creates a NoSuchEvent exception with given String.
     *
     * @param exc String to create exception with
     */
    public InvalidVote(final String exc) {
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
