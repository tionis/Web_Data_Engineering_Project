package Gruppe3.Webseite.web.controller;

import Gruppe3.Webseite.application.exception.EventNameTaken;
import Gruppe3.Webseite.application.service.EventService;
import Gruppe3.Webseite.persistence.entities.Event;
import Gruppe3.Webseite.web.dto.EventDto;
import Gruppe3.Webseite.application.exception.NoSuchEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebseiteController {
    /**
     * Amount of last events to show.
     */
    int lastEventCount = 20;
    /**
     * Amount of top events to show.
     */
    int topEventCount = 3;

    /**
     * Service to communicate with rest of application.
     */
    private final EventService eventService;

    /**
     * Constructs a WebseiteController.
     *
     * @param eventService The eventService object for service methods
     */
    @Autowired
    public WebseiteController(final EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Handles GET requests on path "/add".
     *
     * @param model Model that transfers event between controller and view
     * @return The form page template.
     */
    @GetMapping("/add")
    public String addEvent(final Model model) {
        String[] types = eventService.getTypes();
        model.addAttribute("types", types);
        return "add_event";
    }

    /**
     * Handles GET requests on path "/".
     *
     * @param model Model that transfers event between controller and view
     * @return The home page template.
     */
    @GetMapping("/")
    public String getHome(final Model model) {
        String[] types = eventService.getTypes();
        EventDto[] events = eventService.getLastEvents(lastEventCount);
        EventDto[] topEvents = eventService.getTopEvents(topEventCount);
        model.addAttribute("types", types);
        model.addAttribute("events", events);
        model.addAttribute("topEvents", topEvents);
        return "home";
    }

    /**
     * Handles GET requests on path "/event/{eventname}".
     *
     * @param model Model that transfers eventService between controller and view
     * @param id    Event name
     * @return The event detail page.
     */
    @GetMapping("/event/{id}")
    public String getEvent(Model model, @PathVariable String id) {
        EventDto event;
        try {
            event = eventService.getEventByName(id);
        } catch (NoSuchEvent e) {
            return "error/404";
        }
        model.addAttribute("event", event);
        return "event_detail";
    }

    /**
     * Handles GET search requests on path "/search".
     *
     * @param model Model that transfers eventService between controller and view
     * @param query Querystring
     * @return The search result page.
     */
    @GetMapping("/search")
    public String getSearch(Model model, @RequestParam(value = "q") String query) {
        EventDto[] locations = eventService.searchForEventAfterLocation(query);
        EventDto[] names = eventService.searchForEventAfterName(query);
        model.addAttribute("locations", locations);
        model.addAttribute("names", names);
        return "search";
    }

    /**
     * Handle add event form eventService
     *
     * @param model      model to work with
     * @param eType      type from add_event form
     * @param eName      name from add_event form
     * @param eDesc      description from add_event form
     * @param eDate      date from add_event form
     * @param eLocation  location from add_event form
     * @param eLongitude longitude from add_event form
     * @param eLatitude  latitude from add_event form
     * @return specific template to use for response with thymeleaf
     */
    @RequestMapping(method = {RequestMethod.POST}, value = "/")
    public String createEvent(Model model, @RequestParam String eType, @RequestParam String eName,
                              @RequestParam String eDesc, @RequestParam String eDate,
                              @RequestParam String eLocation, @RequestParam String eLatitude,
                              @RequestParam String eLongitude) {
        // Parse Event
        try {
            eventService.createEvent(eName, eType, eDate, eLocation, eLatitude, eLongitude, eDesc);
        } catch (EventNameTaken e) {
            throw new RuntimeException("Event name taken");
        }
        // Construct Site
        String[] types = eventService.getTypes();
        EventDto[] events = eventService.getLastEvents(20);
        model.addAttribute("types", types);
        model.addAttribute("events", events);
        return "home";
    }
}
