package Gruppe3.Webseite.controller;

import Gruppe3.Webseite.dto.EventDto;
import Gruppe3.Webseite.model.Event;
import Gruppe3.Webseite.service.Data;
import Gruppe3.Webseite.service.NoSuchEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebseiteController {

    private final Data data;

    /**
     * Constructs a WebseiteController
     *
     * @param data The data object for service methods
     */
    @Autowired
    public WebseiteController(Data data) {
        this.data = data;
    }

    /**
     * Handles GET requests on path "/add".
     *
     * @param model Model that transfers data between controller and view
     * @return The form page template.
     */
    @GetMapping("/add")
    public String addEvent(Model model) {
        String[] types = data.getTypes();
        model.addAttribute("types", types);
        return "add_event";
    }

    /**
     * Handles GET requests on path "/".
     *
     * @param model Model that transfers data between controller and view
     * @return The home page template.
     */
    @GetMapping("/")
    public String getHome(Model model) {
        String[] types = data.getTypes();
        Event[] tempEvents = data.getLastEvents(20);
        Event[] tempTopEvents = data.getTopEvents(3);

        // Arrays of Dtos
        EventDto[] events = new EventDto[tempEvents.length];
        EventDto[] topEvents = new EventDto[tempTopEvents.length];

        // Fill Array of Dtos with data
        for (int i = 0; i < tempEvents.length; i++) {
            events[i] = convertToDto(tempEvents[i]);
        }
        for (int i = 0; i < tempTopEvents.length; i++) {
            topEvents[i] = convertToDto(tempTopEvents[i]);
        }
        model.addAttribute("types", types);
        model.addAttribute("events", events);
        model.addAttribute("topEvents", topEvents);
        return "home";
    }

    /**
     * Handles GET requests on path "/event/{eventname}".
     *
     * @param model Model that transfers data between controller and view
     * @param id Eventname
     * @return The event detail page.
     */
    @GetMapping("/event/{id}")
    public String getEvent(Model model, @PathVariable String id) {
        EventDto event = null;
        try {
            event = convertToDto(data.getEventByName(id));
        } catch (NoSuchEvent e) {
            //TODO
        }
        model.addAttribute("event", event);
        return "event_detail";
    }

    /**
     * Handle add event form data
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
        // Check if type is valid
        String[] types = data.getTypes();
        boolean isValid = false;
        for (String compareType : types) {
            if (!isValid) {
                if (eType.equals(compareType)) {
                    isValid = true;
                }
            }
        }
        if (!isValid) {
            return "error";
        }

        // Parse date
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        try {
            startDate = parser.parse(eDate);
        } catch (ParseException e) {
            return "error";
        }

        // Create location string by checking if both field are used
        String locationString;
        if (eLocation.isEmpty()) {
            if (!eLatitude.isEmpty() && !eLongitude.isEmpty()) {
                locationString = eLatitude + " " + eLongitude;
            } else {
                return "error";
            }
        } else if (eLatitude.isEmpty() && eLongitude.isEmpty()) {
            locationString = eLocation;
        } else {
            return "error";
        }

        // Create Event
        Event eventToSave = new Event(eName, eType, startDate, locationString, eDesc);
        try {
            data.saveEvent(eventToSave);
        } catch (NoSuchEvent e) {
            return "error";
        }

        // Construct Site
        Event[] events = data.getLastEvents(20);
        model.addAttribute("types", types);
        model.addAttribute("events", events);
        return "home";
    }

    private EventDto convertToDto(Event event) {
        return new EventDto(event.getName(), event.getType(),
                event.getStartDate(), event.getLocation(),
                event.getDescription(), event.getLikes(), event.getDislikes());
    }


    /*
    @ExceptionHandler(NoSuchEvent.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody NoSuchEvent handleException(NoSuchEvent e) {
        return new NoSuchEvent("Event doesn't exist!");
    }
    */
}
