package Gruppe3.Webseite.controller;

import Gruppe3.Webseite.model.Event;
import Gruppe3.Webseite.model.Vote;
import Gruppe3.Webseite.service.Data;
import Gruppe3.Webseite.service.NoSuchEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RESTController {

    /**
     * Data object to connect to data store.
     */
    private final Data data;

    /**
     * Constructs a RESTController.
     *
     * @param data The data object for service methods
     */
    @Autowired
    public RESTController(final Data data) {
        this.data = data;
    }

    /**
     * Returns the last n events.
     *
     * @param countString The event count to return as string
     * @return Events wrapped in response
     */
    @GetMapping("/api/events")
    public ResponseEntity<Event[]> events(
            @RequestParam(value = "n", defaultValue = "20") final String countString) {
        int count;
        try {
            count = Integer.parseInt(countString);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(data.getLastEvents(count));
    }

    /**
     * Returns the current types.
     *
     * @return Types as Array.
     */
    @GetMapping("/api/types")
    public String[] types() {
        return data.getTypes();
    }

    /**
     * Returns the top n events.
     *
     * @param countString The event count to return as string
     * @return Events wrapped in response
     */
    @GetMapping("/api/top")
    public ResponseEntity<Event[]> top(
            @RequestParam(value = "n", defaultValue = "20") final String countString) {
        int count;
        try {
            count = Integer.parseInt(countString);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(data.getTopEvents(count));
    }

    /**
     * Returns the specified event.
     *
     * @param eventName The event count to return as string
     * @return Event wrapped in response
     */
    @GetMapping("/api/event/{eventName}")
    public ResponseEntity<Event> event(@PathVariable final String eventName) {
        try {
            return ResponseEntity.ok(data.getEventByName(eventName));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Handle API vote adding.
     *
     * @param vote Vote to apply to data store
     * @return Modified event wrapped in response
     */
    @PostMapping("/api/vote/add")
    public ResponseEntity<Event> addVote(@RequestBody final Vote vote) {
        try {
            data.addVote(vote);
            return ResponseEntity.ok(data.getEventByName(vote.getEventName()));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Handle API Vote Removal.
     *
     * @param vote Vote to apply to data store
     * @return Modified event wrapped in response
     */
    @PostMapping("/api/vote/remove")
    public ResponseEntity<Event> removeVote(@RequestBody final Vote vote) {
        try {
            data.removeVote(vote);
            return ResponseEntity.ok(data.getEventByName(vote.getEventName()));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Handle API Event Creation.
     *
     * @param event Event to save to data store
     * @return Modified event wrapped in response
     */
    @PostMapping("/api/create")
    public ResponseEntity<Event> createEvent(@RequestBody final Event event) {
        try {
            data.saveEvent(event);
            return ResponseEntity.ok(data.getEventByName(event.getName()));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Search for an event by location.
     *
     * @param searchQuery The search query string.
     * @return Events found
     */
    @GetMapping("/api/search/location")
    public Event[] locationSearch(
            @RequestParam(value = "q") final String searchQuery) {
        return data.searchForEventAfterLocation(searchQuery);
    }

    /**
     * Search for an event by name.
     *
     * @param searchQuery The search query string.
     * @return Events found
     */
    @GetMapping("/api/search/name")
    public Event[] nameSearch(
            @RequestParam(value = "q") final String searchQuery) {
        return data.searchForEventAfterName(searchQuery);
    }
}
