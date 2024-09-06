package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;

import java.util.List;

public interface ITransaccionService {

    //Obtener todas las transacciones
    List<Transacciones> findAll();

    //Obtener las transacciones por Usuario
    List<Transacciones> findByUsuario(Usuario usuario);

    //Obtener transaccion por id de transaction
    Transacciones findById(Integer id);


    //Nueva transaccion
    Transacciones save(Transacciones transacciones);

    //Eliminar transaccion
    void delete(Integer id);

}
