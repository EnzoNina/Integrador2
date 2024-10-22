package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.dto.TransaccionResponseDto;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ITransaccionService transaccionService;

    //Balance
    @GetMapping("/balance/{id}")
    public BigDecimal balanceTotal(@PathVariable Integer id) {
        return transaccionService.balanceTotal(id);
    }

    //Ingresos por mes
    @GetMapping("/ingresos_por_mes/{id}")
    public Object[] ingresosPorMes(@PathVariable Integer id) {
        return transaccionService.ingresosPorMes(id).toArray();
    }

    //Gastos por mes
    @GetMapping("/gastos_por_mes/{id}")
    public Object[] gastosPorMes(@PathVariable Integer id) {
        return transaccionService.gastosPorMes(id).toArray();
    }

    //Transacciones recientes
    @GetMapping("/transacciones_recientes/{id}")
    public List<TransaccionResponseDto> transaccionesRecientes(@PathVariable Integer id) {
        List<Transacciones> transacciones = transaccionService.transaccionesRecientes(id);

        //Creamos una lista de transacciones de respuesta
        List<TransaccionResponseDto> transaccionesResponseList = new ArrayList<>();
        for (Transacciones transaccion : transacciones) {
            transaccionesResponseList.add(setTransaccionResponseDto(transaccion));
        }

        return transaccionesResponseList;
    }

    //Gastos por categoria
    @GetMapping("/gastos_por_categoria/{id}")
    public Object[] gastosPorCategoria(@PathVariable Integer id) {
        return transaccionService.gastosPorCategoria(id).toArray();
    }

    private TransaccionResponseDto setTransaccionResponseDto(Transacciones transaccion) {
        return TransaccionResponseDto.builder()
                .id(String.valueOf(transaccion.getId()))
                .usuario(transaccion.getIdUsuario().getNombres())
                .categoria(transaccion.getIdCategoria().getDescripcion())
                .tipo_transaccion(transaccion.getIdCategoria().getIdTipoTra().getDescripcion())
                .tipo_concepto(transaccion.getIdConcepto().getDescripcion())
                .frecuencia(transaccion.getIdFrecuencia().getDescripcion())
                .divisa(transaccion.getDivisa().getSimbolo())
                .monto(transaccion.getMonto().toString())
                .descripcion(transaccion.getDescripcion())
                .fecha(transaccion.getFechaTransaccion().toString())
                .build();
    }

}