package com.mrn.springbootjpa.controllers;

import com.mrn.springbootjpa.models.entity.Bills;
import com.mrn.springbootjpa.models.entity.Client;
import com.mrn.springbootjpa.models.entity.Product;
import com.mrn.springbootjpa.models.service.IClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {

    private final IClientService clientService;

    public BillController(IClientService clientService) {
        this.clientService = clientService;
    }

    // the complete url will be /bill/form/1=id
    @GetMapping("/form/{clientId}")
    public String createBill(@PathVariable(value = "clientId") Long clientId, Map<String, Object> model,
                             RedirectAttributes flash) {

        // get client
        Client client = clientService.findById(clientId);
        if (client == null) {
            flash.addFlashAttribute("error", "Client does not exist in DB");
            return "redirect:/show";
        }

        // attach the client to bill
        Bills bill = new Bills();
        bill.setClient(client);

        model.put("bill", bill);
        model.put("title", "Create bill");

        return "bill/form_bill";
    }

    // ResponseBody - Annotation that indicates a method return value should be bound to the web response body.
    // When we are adding @ResponseBody annotation we can't use model interface
    @GetMapping(value = "/load-products/{term}", produces = {"application/json"})
    public @ResponseBody List<Product> loadProducts(@PathVariable String term) {
        return clientService.findByProductName(term);
    }

}
