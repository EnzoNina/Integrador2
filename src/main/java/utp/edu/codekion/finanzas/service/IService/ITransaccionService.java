package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;

import java.math.BigDecimal;
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

    //Sumar transacciones por categoria
    BigDecimal sumarTransaccionesPorCategoriaAndUsuario(Integer id_categoria, Usuario usuario);

    //Balance Total
    BigDecimal balanceTotal(Integer id_usuario);

    //Obtener los ingresos por mes
    List<Object[]> ingresosPorMes(Integer id_usuario);

    //Gastos por mes
    List<Object[]> gastosPorMes(Integer id_usuario);

    //Transacciones recientes
    List<Transacciones> transaccionesRecientes(Integer id_usuario);

    //Gastos por categoria
    List<Object[]> gastosPorCategoria(Integer id_usuario);

}
