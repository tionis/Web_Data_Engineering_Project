package Gruppe3.Webseite.application.init;

import Gruppe3.Webseite.application.service.EventService;
import Gruppe3.Webseite.persistence.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Component
public class DataInit implements CommandLineRunner {

    /**
     * Service for communication with application.
     */
    private final EventService eventService;

    /**
     * Random data source
     */
    private final Random rand;

    /**
     * Value of init property
     */
    @Value("${init}")
    private boolean init;

    /**
     * Initialize the application.
     *
     * @param eventService the service the initializer is created with
     */
    @Autowired
    public DataInit(final EventService eventService) {
        this.eventService = eventService;
        rand = new Random();
    }

    /**
     * Execute the initialization.
     *
     * @param args Input parameters
     */
    @Override
    public void run(final String... args) {
        if (init) {
            String[] types = eventService.getTypes();

            // Add normal example events
            for (int i = 0; i < types.length; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, i);
                Date startDate = cal.getTime();
                eventService.saveEvent(
                        new Event("Event" + (i + 1), types[i],
                                startDate, new Date(), "Munich",
                                null, null, "Eine tolle Beschreibung",
                                rand.nextInt(101), rand.nextInt(101)));
            }

            // Add coordinate based example event
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, 4);
            Date startDate = cal.getTime();
            eventService.saveEvent(new Event("Presidential Party",
                    types[0], startDate, new Date(), null,
                    38.897957, -77.036560,
                    "The best party maybe ever!", 100, 10));
        }
    }
}
