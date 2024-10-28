package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    List<IngresoMesDto> ingresosPorMes(Integer id_usuario);

    //Gastos por mes
    List<IngresoMesDto> gastosPorMes(Integer id_usuario);

    //Transacciones recientes
    List<Transacciones> transaccionesRecientes(Integer id_usuario);

    //Gastos por categoria
    List<CategoriaGastoDto> gastosPorCategoria(Integer id_usuario);

    //Obtener transacciones entre rango de fechas
    List<Transacciones> findByFechaTransaccionBetween(LocalDate fechaInicio, LocalDate fechaFin);

    //Obtener total ingresos entre rango de fechas
    BigDecimal totalIngresosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin,Integer id_usuario);

    //Obtener total egresos entre rango de fechas
    BigDecimal totalEgresosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin,Integer id_usuario);

}
