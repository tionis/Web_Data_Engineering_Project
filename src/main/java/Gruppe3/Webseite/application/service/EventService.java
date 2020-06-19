package Gruppe3.Webseite.application.service;

import Gruppe3.Webseite.application.exception.NoSuchEvent;
import Gruppe3.Webseite.persistence.entities.Event;
import Gruppe3.Webseite.persistence.entities.Vote;
import Gruppe3.Webseite.persistence.repository.Data;
import Gruppe3.Webseite.web.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EventService {

    private final Data data;

    @Autowired
    public EventService(final Data data) {
        this.data = data;
    }

    public String[] getTypes() {
        return data.getTypes();
    }

    public EventDto[] searchForEventAfterLocation(String searchQuery) {
        return convertToDtoArray(data.searchForEventAfterLocation(searchQuery));
    }

    public EventDto[] getTopEvents(int count) {
        return convertToDtoArray(data.getTopEvents(count));
    }

    public EventDto getEventByName(String eventName) throws NoSuchEvent {
        return convertToDto(data.getEventByName(eventName));
    }

    public void addVote(Vote vote) throws NoSuchEvent {
        data.addVote(vote);
    }

    public void removeVote(Vote vote) throws NoSuchEvent {
        data.removeVote(vote);
    }

    public EventDto[] searchForEventAfterName(String searchQuery) {
        return convertToDtoArray(data.searchForEventAfterName(searchQuery));
    }

    public EventDto[] getLastEvents(int n) {
        return convertToDtoArray(data.getLastEvents(n));
    }

    public void saveEvent(Event event) throws NoSuchEvent {
        data.saveEvent(event);
    }

    public void createEvent(String eName, String eType, String eDate, String eLocation, String eLongitude, String eLatitude, String eDesc) {
        // Check if type is valid
        String[] types = getTypes();
        boolean isValid = false;
        for (String compareType : types) {
            if (!isValid) {
                if (eType.equals(compareType)) {
                    isValid = true;
                }
            }
        }
        if (!isValid) {
            throw new RuntimeException("Event Type invalid");
        }

        // Parse date
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        try {
            startDate = parser.parse(eDate);
        } catch (ParseException e) {
            throw new RuntimeException("Date format invalid");
        }

        // Create location string by checking if both field are used
        String locationString;
        if (eLocation.isEmpty()) {
            if (!eLatitude.isEmpty() && !eLongitude.isEmpty()) {
                locationString = eLatitude + " " + eLongitude;
            } else {
                throw new RuntimeException("Only one of the coordinates specified");
            }
        } else if (eLatitude.isEmpty() && eLongitude.isEmpty()) {
            locationString = eLocation;
        } else {
            throw new RuntimeException("Both location and coordinates specified");
        }

        // Create Event
        Event eventToSave = new Event(eName, eType, startDate, locationString, eDesc);
        try {
            saveEvent(eventToSave);
        } catch (NoSuchEvent e) {
            throw new RuntimeException("Event name taken.");
        }

    }

    /**
     * Convert a given event to a event dto
     *
     * @param event Event to transform
     * @return event encoded as dto
     */
    private EventDto convertToDto(Event event) {
        return new EventDto(event.getName(), event.getType(),
                event.getStartDate(), event.getCreationDate(),
                event.getLocation(), event.getDescription(),
                event.getLikes(), event.getDislikes());
    }

    /**
     * Convert a given event array to a event dto array.
     *
     * @param events Events to transform
     * @return event encoded as dto
     */
    private EventDto[] convertToDtoArray(Event[] events) {
        EventDto[] returnArray = new EventDto[events.length];
        for (int i = 0; i < events.length; i++) {
            returnArray[i] = convertToDto(events[i]);
        }
        return returnArray;
    }
}
