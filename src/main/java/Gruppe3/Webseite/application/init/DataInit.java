package Gruppe3.Webseite.application.init;

import Gruppe3.Webseite.application.service.EventService;
import Gruppe3.Webseite.persistence.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInit implements CommandLineRunner {

    /**
     * Service for communication with application.
     */
    private final EventService eventService;

    /**
     * Defines if the initialization should be executed.
     */
    @Value("${init:}")
    private final boolean initEnabled = true;

    /**
     * Initialize the application.
     *
     * @param eventService the service the initializer is created with
     */
    @Autowired
    public DataInit(final EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Execute the initialization.
     *
     * @param args Input parameters
     */
    @Override
    public void run(final String... args) {
        if (initEnabled) {
            String[] types = eventService.getTypes();

            for (int i = 0; i < types.length; i++) {
                eventService.saveEvent(
                        new Event("Event" + (i + 1), types[i], new Date(),
                                "Passau", "Beschreibung"));
            }
        }
    }
}
