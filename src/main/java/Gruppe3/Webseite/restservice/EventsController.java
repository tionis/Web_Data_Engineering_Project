package Gruppe3.Webseite.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {
    @GetMapping("/events")
    public Event event(@RequestParam(value = "n", defaultValue = "20") String count) {
        return new Event("Basic", "Passau", "2020-10-01",
                "TestEvent", "This is a test");
    }
}