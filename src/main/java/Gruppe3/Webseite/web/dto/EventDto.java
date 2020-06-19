package Gruppe3.Webseite.web.dto;

import java.util.Date;

public class EventDto {

    private final String name;
    private final String type;
    private final String location;
    private final Date startDate;
    private final String description;
    private final int dislikes;
    private final int likes;


    public EventDto(final String name, final String type, final Date startDate,
                    final String location, final String description,
                    final int likes, final int dislikes) {
        this.type = type;
        this.location = location;
        this.startDate = startDate;
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

    public Date getStartDate() {
        return startDate;
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
