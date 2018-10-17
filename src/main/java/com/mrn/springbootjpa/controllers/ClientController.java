package com.mrn.springbootjpa.controllers;

import com.mrn.springbootjpa.models.dao.IClientDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {

    private final IClientDAO clientDAO;

    // autowired with the help of the constructor
    public ClientController(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }



    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("title", "List of all clients");
        model.addAttribute("clients", clientDAO.findAll());

        return "show";
    }
}
