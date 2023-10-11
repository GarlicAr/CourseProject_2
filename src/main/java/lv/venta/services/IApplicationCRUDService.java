package lv.venta.services;

import lv.venta.models.Application;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IApplicationCRUDService {

    public List<Application> getAll();

    public void deleteApplication(Long appId);

}
