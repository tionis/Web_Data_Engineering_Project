package Gruppe3.Webseite.controller;

import Gruppe3.Webseite.model.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RESTController {
    @GetMapping("/api/events")
    public Event[] events(@RequestParam(value = "n", defaultValue = "20") String count) {
        // TODO instead of examples get the event array from the getLastEvents(int n) method
        Event[] events = new Event[2];
        events[0] = new Event("Basic", "Passau", new Date(),
                "TestEvent", "This is a test");
        events[1] = new Event("Basic", "Passau", new Date(),
                "TestEvent Followup", "This is a second test");
        return events;
    }
}