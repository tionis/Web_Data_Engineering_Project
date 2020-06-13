package Gruppe3.Webseite.model;

import java.util.Date;

public class Event {

    private final String type;
    private final String location;
    private final Date date; // Use ISO 8601
    private final String name;
    private final String description;
    private int dislikes;
    private int likes;

    public Event(String type, String location, Date date, String name, String description) {
        this.type = type;
        this.location = location;
        this.date = date;
        this.name = name;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLikes() {
        return likes;
    }

    public int getDisLikes() {
        return dislikes;
    }
}
