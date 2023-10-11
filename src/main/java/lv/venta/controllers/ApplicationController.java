package lv.venta.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lv.venta.models.Application;
import lv.venta.repos.IRepoApplication;
import lv.venta.services.IApplicationCRUDService;
import lv.venta.services.impl.ApplicationCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    ApplicationCRUDService appService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/all")
    public String retrieveAllApplications(Model model) {

        Collection<Application> applications = appService.getAll();

        try {

            String json = objectMapper.writeValueAsString(applications);

            System.out.println(json);

            return json;

        } catch (Exception e) {

            e.printStackTrace();

            return "Error";
        }



    }

}
