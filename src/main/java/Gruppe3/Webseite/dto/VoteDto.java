package Gruppe3.Webseite.dto;

public class VoteDto {

    private final String eventName;
    private final boolean isLike;

    public VoteDto(final String eventName, final boolean isLike) {
        this.eventName = eventName;
        this.isLike = isLike;
    }

    public String getEventName() {
        return eventName;
    }

    public boolean isLike() {
        return isLike;
    }
}
