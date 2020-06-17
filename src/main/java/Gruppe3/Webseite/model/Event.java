package Gruppe3.Webseite.model;

import java.util.Date;

public final class Event {

    /**
     * Name of the event.
     */
    private final String name;
    /**
     * Type of event this is.
     */
    private final String type;
    /**
     * Location where the event takes place, this is either a place name or
     * coordinates.
     */
    private final String location;
    /**
     * Date the event starts.
     */
    private final Date startDate;
    /**
     * Date the event was created.
     */
    private final Date creationDate;
    /**
     * Full Description of the event.
     */
    private final String description;
    /**
     * Amount of Dislikes of this event.
     */
    private final int dislikes;
    /**
     * Amount of likes of this event.
     */
    private final int likes;

    /**
     * Create event by specifying all parameters but votes.
     *
     * @param type        Type of event to create
     * @param location    Location of the created event
     * @param startDate   Date the event starts
     * @param name        Name of the Event
     * @param description Full description for the event
     */
    public Event(final String name, final String type, final Date startDate,
                 final String location, final String description) {
        this.type = type;
        this.location = location;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
        this.creationDate = new Date();
        dislikes = 0;
        likes = 0;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Event)) return false;
        Event otherEvent = (Event) other;
        // This may match different event objects as only names are compared,
        // but for this implementations it doesn't matter.
        return name.equals(otherEvent.getName());
    }
}
