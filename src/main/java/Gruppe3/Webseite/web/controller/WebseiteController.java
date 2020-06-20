package Gruppe3.Webseite.web.controller;

import Gruppe3.Webseite.application.service.EventService;
import Gruppe3.Webseite.web.dto.EventDto;
import Gruppe3.Webseite.application.exception.NoSuchEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebseiteController {

    private final EventService eventService;

    /**
     * Constructs a WebseiteController
     *
     * @param eventService The eventService object for service methods
     */
    @Autowired
    public WebseiteController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Handles GET requests on path "/add".
     *
     * @param model Model that transfers eventService between controller and view
     * @return The form page template.
     */
    @GetMapping("/add")
    public String addEvent(Model model) {
        String[] types = eventService.getTypes();
        model.addAttribute("types", types);
        return "add_event";
    }

    /**
     * Handles GET requests on path "/".
     *
     * @param model Model that transfers eventService between controller and view
     * @return The home page template.
     */
    @GetMapping("/")
    public String getHome(Model model) {
        String[] types = eventService.getTypes();
        EventDto[] tempEvents = eventService.getLastEvents(20);
        EventDto[] tempTopEvents = eventService.getTopEvents(3);

        // Arrays of Dtos
        EventDto[] events = new EventDto[tempEvents.length];
        EventDto[] topEvents = new EventDto[tempTopEvents.length];

        // Fill Array of Dtos with eventService
        System.arraycopy(tempEvents, 0, events, 0, tempEvents.length);
        System.arraycopy(tempTopEvents, 0, topEvents, 0, tempTopEvents.length);
        model.addAttribute("types", types);
        model.addAttribute("events", events);
        model.addAttribute("topEvents", topEvents);
        return "home";
    }

    /**
     * Handles GET requests on path "/event/{eventname}".
     *
     * @param model Model that transfers eventService between controller and view
     * @param id    Eventname
     * @return The event detail page.
     */
    @GetMapping("/event/{id}")
    public String getEvent(Model model, @PathVariable String id) {
        EventDto event = null;
        try {
            event = eventService.getEventByName(id);
        } catch (NoSuchEvent e) {
            //TODO HTTP 404 ERROR
        }
        model.addAttribute("event", event);
        return "event_detail";
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
                              @RequestParam String eLocation, @RequestParam String eLongitude,
                              @RequestParam String eLatitude) {
        // Parse Event
        eventService.createEvent(eName, eType, eDate, eLocation, eLongitude, eLatitude, eDesc);

        // Construct Site
        String[] types = eventService.getTypes();
        EventDto[] events = eventService.getLastEvents(20);
        model.addAttribute("types", types);
        model.addAttribute("events", events);
        return "home";
    }
}
