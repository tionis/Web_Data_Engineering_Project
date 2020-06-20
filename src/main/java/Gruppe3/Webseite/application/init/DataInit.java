package Gruppe3.Webseite.application.init;

import Gruppe3.Webseite.application.service.EventService;
import Gruppe3.Webseite.persistence.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInit implements CommandLineRunner {

    private final EventService eventService;

    @Autowired
    public DataInit(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void run(String... args) throws Exception {

        String[] types = eventService.getTypes();

        for (int i = 0; i < types.length; i++) {
            eventService.saveEvent(new Event("Event" + (i+1), types[i], new Date(),
                    "Passau", "Beschreibung"));
        }

    }
}
