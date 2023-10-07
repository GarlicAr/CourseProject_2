package lv.venta.controllers;


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

    @GetMapping("/all")
    public String retrieveAllApplications(Model model) {

        Collection<Application> allApps = appService.getAll();

        model.addAttribute("AllApplications", allApps);

        return "show-all-applications";


    }

}
