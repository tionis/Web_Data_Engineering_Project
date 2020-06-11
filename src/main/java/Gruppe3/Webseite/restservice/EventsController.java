package Gruppe3.Webseite.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {
    @GetMapping("/events")
    public Event[] event(@RequestParam(value = "n", defaultValue = "20") String count) {
        // TODO instead of examples get the event array from the getLastEvents(int n) method
        Event[] events = new Event[2];
        events[0] = new Event("Basic", "Passau", "2020-10-01",
                "TestEvent", "This is a test");
        events[1] = new Event("Basic", "Passau", "2020-10-02",
                "TestEvent Followup", "This is a second test");
        return events;
    }
}