package Gruppe3.Webseite.controller;

import Gruppe3.Webseite.service.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
