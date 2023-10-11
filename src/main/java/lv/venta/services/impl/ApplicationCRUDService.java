package lv.venta.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lv.venta.models.Application;
import lv.venta.repos.IRepoApplication;
import lv.venta.services.IApplicationCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteApplication(Long appId) {

        try {

            Application appl = applicationRepo.findById(appId)
                    .orElseThrow(() -> new EntityNotFoundException("No such application!"));

            applicationRepo.delete(appl);


        }catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}
