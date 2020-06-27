package Gruppe3.Webseite.web.dto;

public class VoteDto {
    /**
     * The name of the event this vote is valid for.
     */
    private final String eventName;

    /**
     * Determines if this vote is a like.
     */
    private final boolean isLike;

    /**
     * Construct a VoteDto.
     *
     * @param eventName Name of the event this Vote is valid for.
     * @param isLike    Determines if this vote is a like.
     */
    public VoteDto(final String eventName, final boolean isLike) {
        this.eventName = eventName;
        this.isLike = isLike;
    }

    /**
     * Get the name of the event this vote is valid for.
     *
     * @return The name of the event.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Get if vote isLike.
     *
     * @return true if vote is like
     */
    public boolean isLike() {
        return isLike;
    }
}
