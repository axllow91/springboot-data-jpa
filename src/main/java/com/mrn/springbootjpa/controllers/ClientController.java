package com.mrn.springbootjpa.controllers;

import com.mrn.springbootjpa.models.dao.IClientDAO;
import com.mrn.springbootjpa.models.entity.Client;
import com.mrn.springbootjpa.models.service.IClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("client") // -> better practice to hide the id(instead of hiding into html)
public class ClientController {

    private final IClientService clientService;

    // autowired with the help of the constructor
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }


    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Model model) {

        model.addAttribute("title", "List of all clients");
        model.addAttribute("clients", clientService.findAll());

        return "show";
    }

    @RequestMapping(value = "/form")
    public String createForm(Map<String, Object> model) {
        Client client = new Client();
        model.put("client", client);
        model.put("title", "Form");

        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String editClient(@PathVariable(value = "id") Long id, Map<String, Object> model) {

        Client client = null;
        if (id > 0)
            client = clientService.findById(id);
        else return "redirect:/show";

        model.put("client", client);
        model.put("title", "Edit client");

        return "form";
    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveForm(@Valid Client client, BindingResult bindingResult, Model model, SessionStatus status) {
        if (bindingResult.hasErrors()) {
            // return the actual form with resulted errors
            model.addAttribute("title", "List of all clients");
            return "form";
        }

        clientService.save(client);
        status.setComplete();
        return "redirect:show";
    }

    @RequestMapping(value="/remove/{id}")
    public String remove(@PathVariable(value = "id") Long id) {

        if(id > 0)
            clientService.deleteById(id);

        // after the operation is finished successfully redirect me to the place i want
        return "redirect:/show";
    }

}
