package lv.venta.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lv.venta.dto.ApplicationDTO;
import lv.venta.models.Application;
import lv.venta.repos.IRepoApplication;
import lv.venta.services.IApplicationCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ApplicationCRUDService implements IApplicationCRUDService {


    @Autowired
    private IRepoApplication applicationRepo;


    @Override
    public List<Application> getAll() {

        return (List<Application>) applicationRepo.findAll();

    }

    @Override
    public Application getApp(Long appId) {

        try{

            Application appl = applicationRepo.findById(appId)
                    .orElseThrow(() -> new EntityNotFoundException("No such application!"));

            return appl;

        }catch (Exception e) {

            System.out.println(e.getMessage());

        }


        return null;

    }

    @Override
    public void deleteApplication(Long appId) {

        try {

            Application appl = applicationRepo.findById(appId)
                    .orElseThrow(() -> new EntityNotFoundException("No such application!"));

            applicationRepo.delete(appl);


        }catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

    @Override
    public void updateApp(Long id, ApplicationDTO updatedApp) {

        try{

            Collection<Application> allApps = (Collection<Application>) applicationRepo.findAll();

            for (Application temp : allApps) {

                if (temp.getApplicationId() == id) {

                    temp.setText(updatedApp.getText());
                    temp.setTitle(updatedApp.getTitle());

                    applicationRepo.save(temp);

                    break;

                }

            }




        }catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}
