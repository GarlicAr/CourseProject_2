package lv.venta.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lv.venta.dto.ApplicationDTO;
import lv.venta.models.Application;
import lv.venta.repos.IRepoApplication;
import lv.venta.services.IApplicationCRUDService;
import lv.venta.services.impl.ApplicationCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

            return json;

        } catch (Exception e) {

            e.printStackTrace();

            return "Error";
        }


    }

    @DeleteMapping("/delete/{appId}")
    public void deleteApplication(@PathVariable("appId") Long appId) {

        try {

            System.out.println(appId);

            if(appId > 0) {

                appService.deleteApplication(appId);


            }
            else {

                System.out.printf("There has been a problem deleting Application with id: " + appId);

            }


        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    @PutMapping("/update/{appId}")
    public void updateApplication(@PathVariable("appId") Long appId, @RequestBody ApplicationDTO appDto) {

        try {

            if(appId > 0) {

                appService.updateApp(appId, appDto);

            }
            else {

                System.out.printf("There has been a problem deleting Application with id: " + appId);

            }


        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }


}
