package lv.venta.services;

import lv.venta.dto.ApplicationDTO;
import lv.venta.models.Application;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IApplicationCRUDService {

    public List<Application> getAll();

    public Application getApp(Long appId);

    public void deleteApplication(Long appId);

    public void updateApp(Long id, ApplicationDTO updatedApp);

}
