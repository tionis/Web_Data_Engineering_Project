package Gruppe3.Webseite.persistence.entities;

public final class Vote {
    /**
     * Name of the event this vote is valid for.
     */
    private final String eventName;
    /**
     * Specifies whether the Vote is like.
     */
    private final boolean isLike;

    /**
     * Constructs a vote for a given event.
     *
     * @param eventName name of the event this vote is valid for
     * @param isLike    whether the vote is a like (or a dislike)
     */
    public Vote(final String eventName, final boolean isLike) {
        this.eventName = eventName;
        this.isLike = isLike;
    }

    /**
     * Get the name of the event of this vote.
     *
     * @return the name of the event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Check whether this is a like or dislike.
     *
     * @return true if this vote is a like
     */
    public boolean getIsLike() {
        return isLike;
    }
}
