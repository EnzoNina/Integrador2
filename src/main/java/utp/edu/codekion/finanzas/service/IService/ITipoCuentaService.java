package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.TipoCuenta;

import java.util.List;

public interface ITipoCuentaService {

    TipoCuenta findById(int id);

    List<TipoCuenta> findAll();

}
