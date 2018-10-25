package com.mrn.springbootjpa.controllers;

import com.mrn.springbootjpa.models.entity.Client;
import com.mrn.springbootjpa.models.service.IClientService;
import com.mrn.springbootjpa.models.service.IUploadFileService;
import com.mrn.springbootjpa.util.paginator.PageRender;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

@Controller
@SessionAttributes("client") // -> better practice to hide the id(instead of hiding into html)
public class ClientController {

    private final IClientService clientService;
    private final IUploadFileService uploadFileService;



    // autowired with the help of the constructor
    public ClientController(IClientService clientService, IUploadFileService uploadFileService) {
        this.clientService = clientService;
        this.uploadFileService = uploadFileService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Client> clients = clientService.findAll(pageRequest);

        PageRender<Client> pageRender = new PageRender<>("/show", clients);

        model.addAttribute("title", "List of all clients");
        model.addAttribute("clients", clients);
        model.addAttribute("page", pageRender);

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
    public String editClient(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        // Create an empty client obj
        Client client = null;

        if (id > 0) {
            client = clientService.findById(id);
            if (client == null) {
                flash.addFlashAttribute("error", "Client doesn't exist in database!");
                return "redirect:/show";
            }
        } else {
            flash.addFlashAttribute("error", "Couldn't find client");
            return "redirect:/show";
        }

        model.put("client", client);
        model.put("title", "Edit client");

        return "form";
    }

    @GetMapping(value = "/uploads/{filename:.+}")
    // spring will truncate the extension because we need to charge this img
    public ResponseEntity<Resource> viewImage(@PathVariable String filename) {

        Resource resource = null;
        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + (resource != null ? resource.getFilename() : null) + "\"")
                .body(resource);
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveForm(@Valid Client client, BindingResult bindingResult, Model model,
                           @RequestParam(name = "file") MultipartFile image,
                           SessionStatus status, RedirectAttributes flash) {

        if (bindingResult.hasErrors()) {
            // return the actual form with resulted errors
            model.addAttribute("title", "List of all clients");
            return "form";
        }

        // upload image
        if (!image.isEmpty()) {

            // check if client exists first && image exists after
            if (client.getId() != null
                    && client.getId() > 0
                    && client.getImage() != null
                    && client.getImage().length() > 0) {

                uploadFileService.delete(client.getImage());
            }
        }

        String uniqueFilename = null;
        try {
            uniqueFilename = uploadFileService.copy(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        flash.addFlashAttribute("info", "Uploaded successfully '" + uniqueFilename + "'");
        client.setImage(uniqueFilename);

        String messageFlash = (client.getId() != null) ? "Client edited successfully!" : "Client created successfully!";

        clientService.save(client);
        status.setComplete();
        flash.addFlashAttribute("success",messageFlash);

        return"redirect:show";
}

    @RequestMapping(value = "/delete/{id}")
    public String remove(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id > 0) {
            Client client = clientService.findById(id);
            clientService.deleteById(id);
            // after the operation is finished successfully redirect me to the place i want
            flash.addFlashAttribute("success", "Client removed successfully!");

            if(uploadFileService.delete(client.getImage())) {
                flash.addFlashAttribute("info", "Image " + client.getImage() + " deleted successfully");
            }
        }
        return "redirect:/show";
    }

    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        // find the client
        Client client = clientService.findById(id);

        // if non existent client
        // return me to show template
        if (client == null) {
            flash.addFlashAttribute("error", "The client does not exist!");
            return "redirect:/show";
        }

        model.put("client", client);
        model.put("title", "Client details: " + client.fullName());

        return "view";
    }
}