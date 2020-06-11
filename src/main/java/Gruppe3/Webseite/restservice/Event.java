package Gruppe3.Webseite.restservice;

public class Event {

    private final String type;
    private final String location;
    private final String date; // Use ISO 8601
    private final String name;
    private final String description;

    public Event(String type, String location, String date, String name, String description) {
        this.type = type;
        this.location = location;
        this.date = date;
        this.name = name;
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public String getLocation() {
        return location;
    }
    public String getDate() {
        return date;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
