package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Transacciones;

import java.util.List;

public interface ITransaccionService {

    //Obtener todas las transacciones
    List<Transacciones> findAll();

    //Nueva transaccion
    Transacciones save(Transacciones transacciones);

    //Eliminar transaccion
    void delete(Integer id);

}
