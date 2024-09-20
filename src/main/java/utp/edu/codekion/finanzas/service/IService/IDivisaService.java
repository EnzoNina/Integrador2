package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Divisa;

import java.util.List;

public interface IDivisaService {

    List<Divisa> findAll();

    Divisa findById(Integer id);

}
