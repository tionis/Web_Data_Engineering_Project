package Gruppe3.Webseite.application.service;

import Gruppe3.Webseite.application.exception.EventNameTaken;
import Gruppe3.Webseite.application.exception.NoSuchEvent;
import Gruppe3.Webseite.persistence.entities.Event;
import Gruppe3.Webseite.persistence.entities.Vote;
import Gruppe3.Webseite.persistence.repository.Data;
import Gruppe3.Webseite.web.dto.EventDto;
import Gruppe3.Webseite.web.dto.VoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EventService {

    /**
     * The data store layer object.
     */
    private final Data data;

    /**
     * Create a basic Event Service.
     *
     * @param data Data store layer object to use.
     */
    @Autowired
    public EventService(final Data data) {
        this.data = data;
    }

    /**
     * Get the currently initialized event types.
     *
     * @return String array of types
     */
    public String[] getTypes() {
        return data.getTypes();
    }

    /**
     * Get the top n events of all time.
     *
     * @param count Amount of events
     * @return Top Events as Dto Array
     */
    public EventDto[] getTopEvents(final int count) {
        return convertEventsToDtoArray(data.getTopEvents(count));
    }

    /**
     * Get event with a given name.
     *
     * @param eventName The name of the event
     * @return Event as dto
     * @throws NoSuchEvent thrown when no event with given name exists
     */
    public EventDto getEventByName(final String eventName) throws NoSuchEvent {
        return convertEventToDto(data.getEventByName(eventName));
    }

    /**
     * Add Vote to specified event.
     *
     * @param vote The vote to add to data store
     * @throws NoSuchEvent Thrown when no event with given Name exists.
     */
    public void addVote(final VoteDto vote) throws NoSuchEvent {
        data.addVote(convertVoteDtoToVote(vote));
    }

    /**
     * Remove Vote from specified event.
     *
     * @param vote The vote to remove from data store
     * @throws NoSuchEvent Thrown when no event with given Name exists.
     */
    public void removeVote(final VoteDto vote) throws NoSuchEvent {
        data.removeVote(convertVoteDtoToVote(vote));
    }

    /**
     * Search for events after location.
     *
     * @param searchQuery The string to search for
     * @return Events found in an EventDto Array
     */
    public EventDto[] searchForEventAfterLocation(final String searchQuery) {
        return convertEventsToDtoArray(data.searchForEventAfterLocation(searchQuery));
    }

    /**
     * Search for events after name.
     *
     * @param searchQuery The string to search for
     * @return Events found in an EventDto Array
     */
    public EventDto[] searchForEventAfterName(final String searchQuery) {
        return convertEventsToDtoArray(data.searchForEventAfterName(searchQuery));
    }

    /**
     * Get the n last added events, but only future ones.
     *
     * @param n Amount of events
     * @return Events add Array Dtos
     */
    public EventDto[] getLastEvents(final int n) {
        return convertEventsToDtoArray(data.getLastEvents(n));
    }

    /**
     * Save the given Event to data store.
     *
     * @param event the event to save to data store
     * @throws EventNameTaken - thrown when an event with the same name already exists
     */
    public void saveEvent(final Event event) throws EventNameTaken {
        data.saveEvent(event);
    }

    /**
     * Handle add event from controller for add_event form.
     *
     * @param eType      type from add_event form
     * @param eName      name from add_event form
     * @param eDesc      description from add_event form
     * @param eDate      date from add_event form
     * @param eLocation  location from add_event form
     * @param eLongitude longitude from add_event form
     * @param eLatitude  latitude from add_event form
     */
    public void createEvent(final String eName, final String eType,
                            final String eDate, final String eLocation,
                            final String eLongitude, final String eLatitude,
                            final String eDesc) {
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
        } catch (EventNameTaken e) {
            throw new RuntimeException("Event name taken.");
        }

    }

    /**
     * Convert a VoteDto to a regular Vote Entity.
     *
     * @param voteDto VoteDto to transform
     * @return Transformed Vote Entity
     */
    private Vote convertVoteDtoToVote(final VoteDto voteDto) {
        return new Vote(voteDto.getEventName(), voteDto.isLike());
    }

    /**
     * Convert a given event to a event dto.
     *
     * @param event Event to transform
     * @return event encoded as dto
     */
    private EventDto convertEventToDto(final Event event) {
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
    private EventDto[] convertEventsToDtoArray(final Event[] events) {
        EventDto[] returnArray = new EventDto[events.length];
        for (int i = 0; i < events.length; i++) {
            returnArray[i] = convertEventToDto(events[i]);
        }
        return returnArray;
    }
}
