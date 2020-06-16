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

    private final Data data;

    /**
     * Constructs a WebseiteController
     *
     * @param data The data object for service methods
     */
    @Autowired
    public RESTController(Data data) {
        this.data = data;
    }

    /**
     * Returns the last n events
     *
     * @param countString The event count to return as string
     * @return Events with all associated metadata
     */
    @GetMapping("/api/events")
    public ResponseEntity<Event[]> events(@RequestParam(value = "n", defaultValue = "20") String countString) {
        int count;
        try {
            count = Integer.parseInt(countString);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(data.getLastEvents(count));
    }

    /**
     * @param vote Vote to apply to datastore
     * @return Modified event wrapped in response
     */
    @PostMapping("/api/vote/add")
    public ResponseEntity<Event> addVote(@RequestBody Vote vote) {
        try {
            data.addVote(vote);
            return ResponseEntity.ok(data.getEventByName(vote.getEventName()));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Remove Like for a given Event
     *
     * @param vote Vote to apply to datastore
     * @return Modified event wrapped in response
     */
    @PostMapping("/api/vote/remove")
    public ResponseEntity<Event> removeVote(@RequestBody Vote vote) {
        try {
            data.removeVote(vote);
            return ResponseEntity.ok(data.getEventByName(vote.getEventName()));
        } catch (NoSuchEvent e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}