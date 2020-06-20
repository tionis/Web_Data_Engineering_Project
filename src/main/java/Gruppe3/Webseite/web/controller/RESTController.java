package Gruppe3.Webseite.web.controller;

import Gruppe3.Webseite.application.exception.EventNameTaken;
import Gruppe3.Webseite.application.service.EventService;
import Gruppe3.Webseite.web.dto.EventDto;
import Gruppe3.Webseite.persistence.entities.Event;
import Gruppe3.Webseite.application.exception.NoSuchEvent;
import Gruppe3.Webseite.web.dto.VoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTController {

    /**
     * EventService object to connect to service.
     */
    private final EventService eventService;

    /**
     * Constructs a RESTController.
     *
     * @param eventService The eventService object for service methods
     */
    @Autowired
    public RESTController(final EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Returns the last n events.
     *
     * @param countString The event count to return as string
     * @return Events wrapped in response
     */
    @GetMapping("/api/events")
    public ResponseEntity<EventDto[]> events(
            @RequestParam(value = "n", defaultValue = "20") final String countString) {
        int count;
        try {
            count = Integer.parseInt(countString);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(eventService.getLastEvents(count));
    }

    /**
     * Returns the current types.
     *
     * @return Types as Array.
     */
    @GetMapping("/api/types")
    public String[] types() {
        return eventService.getTypes();
    }

    /**
     * Returns the top n events.
     *
     * @param countString The event count to return as string
     * @return Events wrapped in response
     */
    @GetMapping("/api/top")
    public ResponseEntity<EventDto[]> top(
            @RequestParam(value = "n", defaultValue = "20") final String countString) {
        int count;
        try {
            count = Integer.parseInt(countString);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(eventService.getTopEvents(count));
    }

    /**
     * Returns the specified event.
     *
     * @param eventName The event count to return as string
     * @return Event wrapped in response
     */
    @GetMapping("/api/event/{eventName}")
    public ResponseEntity<EventDto> event(@PathVariable final String eventName) {
        try {
            return ResponseEntity.ok(eventService.getEventByName(eventName));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Handle API vote adding.
     *
     * @param vote Vote to apply to eventService store
     * @return Modified event wrapped in response
     */
    @PostMapping("/api/vote/add")
    public ResponseEntity<EventDto> addVote(@RequestBody final VoteDto vote) {
        try {
            eventService.addVote(vote);
            return ResponseEntity.ok(eventService.getEventByName(vote.getEventName()));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Handle API Vote Removal.
     *
     * @param vote Vote to apply to eventService store
     * @return Modified event wrapped in response
     */
    @PostMapping("/api/vote/remove")
    public ResponseEntity<EventDto> removeVote(@RequestBody final VoteDto vote) {
        try {
            eventService.removeVote(vote);
            return ResponseEntity.ok(eventService.getEventByName(vote.getEventName()));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Handle API Event Creation.
     *
     * @param event Event to save to eventService store
     * @return Modified event wrapped in response
     */
    @PostMapping("/api/create")
    public ResponseEntity<Event> createEvent(@RequestBody final Event event) {
        eventService.saveEvent(event);
        return ResponseEntity.ok(event);
    }

    /**
     * Search for an event by location.
     *
     * @param searchQuery The search query string.
     * @return Events found
     */
    @GetMapping("/api/search/location")
    public EventDto[] locationSearch(
            @RequestParam(value = "q") final String searchQuery) {
        return eventService.searchForEventAfterLocation(searchQuery);
    }

    /**
     * Search for an event by name.
     *
     * @param searchQuery The search query string.
     * @return Events found
     */
    @GetMapping("/api/search/name")
    public EventDto[] nameSearch(
            @RequestParam(value = "q") final String searchQuery) {
        return eventService.searchForEventAfterName(searchQuery);
    }
}
