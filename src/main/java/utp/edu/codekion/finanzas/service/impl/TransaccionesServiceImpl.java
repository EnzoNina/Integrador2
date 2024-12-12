package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import utp.edu.codekion.finanzas.model.Cuenta;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;
import utp.edu.codekion.finanzas.repository.TransaccionRepository;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionesServiceImpl implements ITransaccionService {

    //Repository
    private final TransaccionRepository transaccionRepository;


    @Override
    public List<Transacciones> findAll() {
        return transaccionRepository.findAll();
    }

    @Override
    public List<Transacciones> findByUsuario(Usuario usuario) {
        return transaccionRepository.findByUsuario(usuario);
    }

    @Override
    public Transacciones findById(Integer id) {
        return transaccionRepository.findById(id).orElse(null);
    }

    @Override
    public Transacciones save(Transacciones transacciones) {
        return transaccionRepository.save(transacciones);
    }

    @Override
    public void delete(Integer id) {
        Transacciones transaccion = transaccionRepository.findById(id).orElse(null);
        assert transaccion != null;
        transaccionRepository.delete(transaccion);
    }

    @Override
    public BigDecimal sumarTransaccionesPorCategoriaAndUsuario(Integer id_categoria, Usuario usuario) {
        return transaccionRepository.sumarTransaccionesPorCategoriaAndUsuario(id_categoria, usuario);
    }

    @Override
    public BigDecimal balanceTotal(Integer id_usuario,Integer id_cuenta) {
        return transaccionRepository.balanceTotal(id_usuario, id_cuenta);
    }

    @Override
    public List<IngresoMesDto> ingresosPorMes(Integer id_usuario) {
        return transaccionRepository.ingresosPorMes(id_usuario);
    }

    @Override
    public List<IngresoMesDto> gastosPorMes(Integer id_usuario) {
        return transaccionRepository.gastosPorMes(id_usuario);
    }

    @Override
    public List<Transacciones> transaccionesRecientes(Integer id_usuario) {
        return transaccionRepository.transaccionesRecientes(id_usuario);
    }

    @Override
    public List<CategoriaGastoDto> gastosPorCategoria(Integer id_usuario) {
        return transaccionRepository.gastosPorCategoria(id_usuario);
    }

    @Override
    public List<Transacciones> findByFechaTransaccionBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return transaccionRepository.findByFechaTransaccionBetween(fechaInicio, fechaFin);
    }

    @Override
    public List<Transacciones> findByFechaTransaccionBetweenAndUsuario(LocalDate fechaInicio, LocalDate fechaFin, Integer id_usuario) {
        return transaccionRepository.findByFechaTransaccionBetweenAndUsuario(fechaInicio, fechaFin, id_usuario);
    }

    @Override
    public BigDecimal totalIngresosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin,Integer id_usuario) {
        return transaccionRepository.totalIngresosEntreFechas(fechaInicio, fechaFin, id_usuario);
    }

    @Override
    public BigDecimal totalEgresosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin,Integer id_usuario) {
        return transaccionRepository.totalEgresosEntreFechas(fechaInicio, fechaFin, id_usuario);
    }

    @Override
    public List<Transacciones> findByUsuarioAndCuenta(Usuario usuario, Cuenta cuenta) {
        return transaccionRepository.findByUsuarioAndCuenta(usuario, cuenta);
    }

    @Override
    public List<IngresoMesDto> ingresosPorMesAndCuenta(Integer id_usuario, Integer id_cuenta) {
        return transaccionRepository.ingresosPorMesAndCuenta(id_usuario, id_cuenta);
    }

    @Override
    public List<IngresoMesDto> gastosPorMesAndCuenta(Integer id_usuario, Integer id_cuenta) {
        return transaccionRepository.gastosPorMesAndCuenta(id_usuario, id_cuenta);
    }

    @Override
    public List<Transacciones> transaccionesRecientesAndCuenta(Integer id_usuario, Integer id_cuenta) {
        return transaccionRepository.transaccionesRecientesAndCuenta(id_usuario, id_cuenta);
    }

    @Override
    public List<CategoriaGastoDto> gastosPorCategoriaAndCuenta(Integer id_usuario, Integer id_cuenta) {
        return transaccionRepository.gastosPorCategoriaAndCuenta(id_usuario, id_cuenta);
    }

}
