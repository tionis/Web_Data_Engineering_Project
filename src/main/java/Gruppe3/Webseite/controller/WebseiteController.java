package Gruppe3.Webseite.controller;

import Gruppe3.Webseite.model.Event;
import Gruppe3.Webseite.service.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebseiteController {

    private Data data;

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
    @GetMapping("add")
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
        Event[] events = data.getLastEvents(20);
        model.addAttribute("types", types);
        model.addAttribute("events", events);
        return "home";
    }

    /**
     *
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "submit.html")
    @ResponseBody
    public String getQuery(@RequestParam String eType, @RequestParam String eName, @RequestParam String eDesc, @RequestParam String eDate, @RequestParam String eLocation) {
        //TODO: wenn datenbank fertig
        return "edit here";
    }


}
