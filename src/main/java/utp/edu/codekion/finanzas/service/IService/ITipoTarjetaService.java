package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.TipoTarjeta;

import java.util.List;

public interface ITipoTarjetaService {

    TipoTarjeta findById(int id);

    List<TipoTarjeta> findAll();

}
