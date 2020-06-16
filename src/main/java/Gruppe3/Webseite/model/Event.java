package Gruppe3.Webseite.model;

import java.util.Date;

public final class Event {

    /**
     * Type of event this is.
     */
    private final String type;
    /**
     * Location where the event takes place.
     */
    private final String location;
    /**
     * Date the event starts.
     */
    private final Date date;
    /**
     * Date the event was created.
     */
    private final Date creationDate;
    /**
     * Name of the event.
     */
    private final String name;
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
     * @param date        Date the event starts
     * @param name        Name of the Event
     * @param description Full description for the event
     */
    public Event(final String type, final String location, final Date date,
                 final String name, final String description) {
        this.type = type;
        this.location = location;
        this.date = date;
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

    public Date getDate() {
        return date;
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
}
