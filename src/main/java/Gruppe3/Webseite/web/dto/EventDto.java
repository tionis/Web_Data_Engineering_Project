package Gruppe3.Webseite.web.dto;

import java.util.Date;

public class EventDto {

    /**
     * Name of the event
     */
    private final String name;

    /**
     * Type of the event.
     */
    private final String type;

    /**
     * Location of the event if no coordinates are given.
     */
    private final String location;

    /**
     * Longitude of the event
     */
    private final Double longitude;

    /**
     * Latitude of the event.
     */
    private final Double latitude;

    /**
     * Date the event starts on.
     */
    private final Date startDate;

    /**
     * Date the event is created on.
     */
    private final Date creationDate;

    /**
     * Description of the event.
     */
    private final String description;

    /**
     * Dislikes the event has received so far.
     */
    private final int dislikes;

    /**
     * Likes the event has received so far.
     */
    private final int likes;

    /**
     * Create a event dto.
     *
     * @param name         Name of the event to create
     * @param type         Type of the event to create
     * @param startDate    startDate of the event to create
     * @param creationDate creationDate of the event
     * @param location     location of the event if no coordinates are specified
     * @param longitude    longitude of the event
     * @param latitude     latitude of the event
     * @param description  description of the event
     * @param likes        likes so far
     * @param dislikes     dislikes so far
     */
    public EventDto(final String name, final String type, final Date startDate,
                    final Date creationDate, final String location,
                    final Double latitude, final Double longitude,
                    final String description, final int likes, final int dislikes) {
        this.type = type;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.startDate = startDate;
        this.creationDate = creationDate;
        this.name = name;
        this.description = description;
        this.dislikes = dislikes;
        this.likes = likes;
    }

    public String getName() {
        return name;
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

    public String getDescription() {
        return description;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getLikes() {
        return likes;
    }
}
