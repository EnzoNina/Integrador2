package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import utp.edu.codekion.finanzas.model.Cuenta;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transacciones, Integer> {

    @Query("SELECT t FROM Transacciones t WHERE t.idUsuario = ?1")
    List<Transacciones> findByUsuario(Usuario usuario);

    @Query("SELECT t FROM Transacciones t WHERE t.idUsuario = ?1 AND t.cuenta = ?2")
    List<Transacciones> findByUsuarioAndCuenta(Usuario usuario, Cuenta cuenta);

    @Query("SELECT SUM(t.monto) FROM Transacciones t WHERE t.idCategoria.id = ?1 AND t.idUsuario = ?2")
    BigDecimal sumarTransaccionesPorCategoriaAndUsuario(Integer idCategoria, Usuario usuario);

    @Query("SELECT SUM(CASE WHEN t.idCategoria.idTipoTra.id = 1 THEN t.monto ELSE 0 END) - SUM(CASE WHEN t.idCategoria.idTipoTra.id = 2 THEN t.monto ELSE 0 END) FROM Transacciones t WHERE t.idUsuario.id = ?1 AND t.cuenta.id = ?2")
    BigDecimal balanceTotal(Integer idUsuario, Integer id_cuenta);

    @Query("SELECT new utp.edu.codekion.finanzas.model.dto.IngresoMesDto(TO_CHAR(t.fechaTransaccion, 'YYYY-MM'), SUM(t.monto)) " +
            "FROM Transacciones t " +
            "JOIN t.idUsuario u " +
            "JOIN t.idCategoria uc " +
            "JOIN uc.idTipoTra tc " +
            "WHERE u.id = ?1 AND tc.descripcion = 'Ingreso' " +
            "GROUP BY TO_CHAR(t.fechaTransaccion, 'YYYY-MM') " +
            "ORDER BY TO_CHAR(t.fechaTransaccion, 'YYYY-MM')")
    List<IngresoMesDto> ingresosPorMes(Integer idUsuario);

    @Query("SELECT new utp.edu.codekion.finanzas.model.dto.IngresoMesDto(TO_CHAR(t.fechaTransaccion, 'YYYY-MM'), SUM(t.monto)) " +
            "FROM Transacciones t " +
            "JOIN t.idCategoria uc " +
            "JOIN uc.idTipoTra tc " +
            "WHERE t.idUsuario.id = ?1 AND t.cuenta.id = ?2 AND tc.descripcion = 'Ingreso' " +
            "GROUP BY TO_CHAR(t.fechaTransaccion, 'YYYY-MM')")
    List<IngresoMesDto> ingresosPorMesAndCuenta(Integer idUsuario, Integer idCuenta);

    @Query("SELECT new utp.edu.codekion.finanzas.model.dto.IngresoMesDto(TO_CHAR(t.fechaTransaccion, 'YYYY-MM'), SUM(t.monto)) " +
            "FROM Transacciones t " +
            "JOIN t.idUsuario u " +
            "JOIN t.idCategoria uc " +
            "JOIN uc.idTipoTra tc " +
            "WHERE u.id = ?1 AND tc.descripcion = 'Egreso' " +
            "GROUP BY TO_CHAR(t.fechaTransaccion, 'YYYY-MM') " +
            "ORDER BY TO_CHAR(t.fechaTransaccion, 'YYYY-MM')")
    List<IngresoMesDto> gastosPorMes(Integer idUsuario);

    @Query("SELECT new utp.edu.codekion.finanzas.model.dto.IngresoMesDto(TO_CHAR(t.fechaTransaccion, 'YYYY-MM'), SUM(t.monto)) " +
            "FROM Transacciones t " +
            "JOIN t.idCategoria uc " +
            "JOIN uc.idTipoTra tc " +
            "WHERE t.idUsuario.id = ?1 AND t.cuenta.id = ?2 AND tc.descripcion = 'Egreso' " +
            "GROUP BY TO_CHAR(t.fechaTransaccion, 'YYYY-MM')")
    List<IngresoMesDto> gastosPorMesAndCuenta(Integer idUsuario, Integer idCuenta);

    @Query("SELECT t FROM Transacciones t WHERE t.idUsuario.id = ?1 ORDER BY t.fechaTransaccion DESC LIMIT 10")
    List<Transacciones> transaccionesRecientes(Integer idUsuario);

    @Query("SELECT t FROM Transacciones t WHERE t.idUsuario.id = ?1 AND t.cuenta.id = ?2 ORDER BY t.fechaTransaccion DESC LIMIT 10")
    List<Transacciones> transaccionesRecientesAndCuenta(Integer idUsuario, Integer idCuenta);

    @Query("SELECT new utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto(uc.descripcion, SUM(t.monto)) " +
            "FROM Transacciones t " +
            "JOIN t.idUsuario u " +
            "JOIN t.idCategoria uc " +
            "JOIN uc.idTipoTra tc " +
            "WHERE u.id = ?1 AND tc.descripcion = 'Egreso' " +
            "GROUP BY uc.descripcion")
    List<CategoriaGastoDto> gastosPorCategoria(Integer idUsuario);

    @Query("SELECT new utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto(uc.descripcion, SUM(t.monto)) " +
            "FROM Transacciones t " +
            "JOIN t.idUsuario u " +
            "JOIN t.cuenta c " +
            "JOIN t.idCategoria uc " +
            "JOIN uc.idTipoTra tc " +
            "WHERE u.id = ?1 AND c.id = ?2 AND tc.descripcion = 'Egreso' " +
            "GROUP BY uc.descripcion")
    List<CategoriaGastoDto> gastosPorCategoriaAndCuenta(Integer idUsuario, Integer idCuenta);

    @Query("SELECT t FROM Transacciones t WHERE t.fechaTransaccion BETWEEN ?1 AND ?2")
    List<Transacciones> findByFechaTransaccionBetween(LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT SUM(t.monto) FROM Transacciones t WHERE t.fechaTransaccion BETWEEN ?1 AND ?2 and t.idCategoria.idTipoTra.id = 1 and t.idUsuario.id = ?3")
    BigDecimal totalIngresosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin, Integer id_usuario);

    @Query("SELECT SUM(t.monto) FROM Transacciones t WHERE t.fechaTransaccion BETWEEN ?1 AND ?2 and t.idCategoria.idTipoTra.id = 2 and t.idUsuario.id = ?3")
    BigDecimal totalEgresosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin, Integer id_usuario);
}