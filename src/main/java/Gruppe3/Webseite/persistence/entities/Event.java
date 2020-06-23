package Gruppe3.Webseite.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

@Entity
public class Event {

    /**
     * Name of the event.
     */
    @Id
    private String name;
    /**
     * Type of event this is.
     */
    private String type;

    /**
     * Location where the event takes place if no coordinates are specified.
     */
    private String location;

    /**
     * Longitude of the location if no location string is given.
     */
    private Double longitude;

    /**
     * Latitude of the location if no location string is given.
     */
    private Double latitude;

    /**
     * Date the event starts.
     */
    private Date startDate;

    /**
     * Date the event was created.
     */
    private Date creationDate;

    /**
     * Full Description of the event.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Amount of Dislikes of this event.
     */
    private int dislikes;

    /**
     * Amount of likes of this event.
     */
    private int likes;

    /**
     * Dummy constructor
     */
    public Event() {
        this.type = "";
        this.location = "";
        this.longitude = null;
        this.latitude = null;
        this.startDate = new Date();
        this.name = "";
        this.description = "";
        this.creationDate = new Date();
        dislikes = 0;
        likes = 0;
    }


    /**
     * Create event by specifying all parameters but votes and creation date.
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
        this.latitude = null;
        this.longitude = null;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
        this.creationDate = new Date();
        dislikes = 0;
        likes = 0;
    }

    /**
     * Create event by specifying all parameters but votes and creation date.
     *
     * @param type        Type of event to create
     * @param location    Location of the created event
     * @param longitude   Longitude of the event location
     * @param latitude    Latitude of the event location
     * @param startDate   Date the event starts
     * @param name        Name of the Event
     * @param description Full description for the event
     */
    public Event(final String name, final String type, final Date startDate,
                 final String location, final Double latitude,
                 final Double longitude, final String description) {
        this.type = type;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
        this.creationDate = new Date();
        dislikes = 0;
        likes = 0;
    }

    /**
     * Create event by specifying all parameters but votes and creation date.
     *
     * @param type        Type of event to create
     * @param longitude   Longitude of the event location
     * @param latitude    Latitude of the event location
     * @param startDate   Date the event starts
     * @param name        Name of the Event
     * @param description Full description for the event
     */
    public Event(final String name, final String type, final Date startDate,
                 final Double longitude, final Double latitude,
                 final String description) {
        this.type = type;
        this.location = null;
        this.longitude = longitude;
        this.latitude = latitude;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
        this.creationDate = new Date();
        dislikes = 0;
        likes = 0;
    }

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
                 final Date creationDate, final String location,
                 final String description) {
        this.type = type;
        this.location = location;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        dislikes = 0;
        likes = 0;
    }

    /**
     * Create event by specifying all parameters.
     *
     * @param type        Type of event to create
     * @param location    Location of the created event
     * @param latitude    Latitude of event if specified
     * @param longitude   Longitude of event if specified
     * @param startDate   Date the event starts
     * @param name        Name of the Event
     * @param description Full description for the event
     */
    public Event(final String name, final String type, final Date startDate,
                 final Date creationDate, final String location,
                 final Double latitude, final Double longitude,
                 final String description, final int likes, final int dislikes) {
        this.type = type;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.dislikes = dislikes;
        this.likes = likes;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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
