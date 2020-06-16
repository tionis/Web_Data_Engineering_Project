package Gruppe3.Webseite.model;

public class Vote {
    private final String eventName;
    private final boolean isLike;

    /**
     * Constructs a vote for a given event
     *
     * @param eventName name of the event this vote is valid for
     * @param isLike    whether the vote is a like (or a dislike)
     */
    public Vote(String eventName, boolean isLike) {
        this.eventName = eventName;
        this.isLike = isLike;
    }

    public String getEventName() {
        return eventName;
    }

    public boolean getIsLike() {
        return isLike;
    }
}
