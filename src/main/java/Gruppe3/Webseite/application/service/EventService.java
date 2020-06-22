package Gruppe3.Webseite.application.service;

import Gruppe3.Webseite.application.exception.NoSuchEvent;
import Gruppe3.Webseite.persistence.entities.Event;
import Gruppe3.Webseite.persistence.repository.EventRepository;
import Gruppe3.Webseite.web.dto.EventDto;
import Gruppe3.Webseite.web.dto.VoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class EventService {

    /**
     * The data repository.
     */
    private final EventRepository repository;

    /**
     * The allowed event types.
     */
    @Value("${types:}")
    private final String[] types;

    /**
     * Create a basic Event Service.
     *
     * @param repository Repository to use for data access.
     */
    @Autowired
    public EventService(final EventRepository repository) {
        this.repository = repository;
        types = new String[]{};
    }

    /**
     * Get the currently initialized event types.
     *
     * @return String array of types
     */
    public String[] getTypes() {
        return types;
    }

    /**
     * Get the top n events of all time.
     *
     * @param count Amount of events
     * @return Top Events as Dto Array
     */
    public EventDto[] getTopEvents(final int count) {
        Event[] tmpArray = repository.getTopEvents(count);
        return convertEventsToDtoArray(tmpArray);
    }

    /**
     * Get event with a given name.
     *
     * @param eventName The name of the event
     * @return Event as dto
     * @throws NoSuchEvent thrown when no event with given name exists
     */
    public EventDto getEventByName(final String eventName) throws NoSuchEvent {
        return convertEventToDto(getEventObjectPerName(eventName));
    }

    /**
     * Add Vote to specified event.
     *
     * @param vote The vote to add to data store
     * @throws NoSuchEvent Thrown when no event with given Name exists.
     */
    public void addVote(final VoteDto vote) throws NoSuchEvent {
        Event eventToEdit = getEventObjectPerName(vote.getEventName());
        if (vote.isLike()) {
            eventToEdit.setLikes(eventToEdit.getLikes() + 1);
        } else {
            eventToEdit.setDislikes(eventToEdit.getDislikes() + 1);
        }
        repository.save(eventToEdit);
    }

    /**
     * Remove Vote from specified event.
     *
     * @param vote The vote to remove from data store
     * @throws NoSuchEvent Thrown when no event with given Name exists.
     */
    public void removeVote(final VoteDto vote) throws NoSuchEvent {
        Event eventToEdit = getEventObjectPerName(vote.getEventName());
        if (vote.isLike()) {
            eventToEdit.setLikes(eventToEdit.getLikes() - 1);
        } else {
            eventToEdit.setDislikes(eventToEdit.getDislikes() - 1);
        }
        repository.save(eventToEdit);
    }

    /**
     * Search for events after location.
     *
     * @param searchQuery The string to search for
     * @return Events found in an EventDto Array
     */
    public EventDto[] searchForEventAfterLocation(final String searchQuery) {
        return convertEventsToDtoArray(
                repository.searchAfterLocation(searchQuery));
    }

    /**
     * Search for events after name.
     *
     * @param searchQuery The string to search for
     * @return Events found in an EventDto Array
     */
    public EventDto[] searchForEventAfterName(final String searchQuery) {
        return convertEventsToDtoArray(repository.searchAfterName(searchQuery));
    }

    /**
     * Get the n last added events, but only future ones.
     *
     * @param n Amount of events
     * @return Events add Array Dtos
     */
    public EventDto[] getLastEvents(final int n) {
        Event[] tmpArray = repository.getLastEvents(n);
        return convertEventsToDtoArray(tmpArray);
    }

    /**
     * Save the given Event to data store.
     *
     * @param event the event to save to data store
     */
    public void saveEvent(final Event event) {
        repository.save(event);
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
        // Check if name is available
        boolean nameAvailable = false;
        try {
            getEventByName(eName);
        } catch (NoSuchEvent e) {
            nameAvailable = true;
        }
        if (!nameAvailable) {
            throw new RuntimeException("Event Name taken.");
        }

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
        String locationString = null;
        Double longitude = null;
        Double latitude = null;
        if (eLocation.isEmpty()) {
            if (!eLatitude.isEmpty() && !eLongitude.isEmpty()) {
                try {
                    latitude = Double.parseDouble(eLatitude);
                    longitude = Double.parseDouble(eLongitude);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Longitude or Latitude invalid.");
                }
            } else {
                throw new RuntimeException(
                        "Only one of the coordinates specified");
            }
        } else if (eLatitude.isEmpty() && eLongitude.isEmpty()) {
            locationString = eLocation;
        } else {
            throw new RuntimeException(
                    "Both location and coordinates specified");
        }

        // Create Event
        Event eventToSave =
                new Event(eName, eType, startDate,
                        locationString, longitude, latitude, eDesc);
        saveEvent(eventToSave);
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
                event.getLocation(), event.getLongitude(), event.getLatitude()
                , event.getDescription(), event.getLikes(), event.getDislikes());
    }

    private Event getEventObjectPerName(String eventName) throws NoSuchEvent {
        Optional<Event> result = repository.findById(eventName);
        if (!result.isPresent()) {
            throw new NoSuchEvent("Event not found");
        }
        return result.get();
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
