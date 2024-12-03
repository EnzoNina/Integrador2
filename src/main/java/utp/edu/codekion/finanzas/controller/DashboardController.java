package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;
import utp.edu.codekion.finanzas.model.dto.TransaccionResponseDto;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Log
public class DashboardController {

    private final ITransaccionService transaccionService;

    //Balance
    @GetMapping("/balance/{id}/{id_cuenta}")
    public BigDecimal balanceTotal(@PathVariable Integer id, @PathVariable Integer id_cuenta) {
        return transaccionService.balanceTotal(id,id_cuenta);
    }

    //Ingresos por mes
    @GetMapping("/ingresos_por_mes/{id}")
    public ResponseEntity<?> ingresosPorMes(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        List<IngresoMesDto> lstIngresos = transaccionService.ingresosPorMes(id);
        for(IngresoMesDto ingreso : lstIngresos){
            log.info(ingreso.toString());
        }
        response.put("ingresos", lstIngresos);
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/ingresos_por_mes/{id}/{id_cuenta}")
    public ResponseEntity<?> ingresosPorMesAndCuenta(@PathVariable Integer id, @PathVariable Integer id_cuenta) {
        Map<String, Object> response = new HashMap<>();
        List<IngresoMesDto> lstIngresos = transaccionService.ingresosPorMesAndCuenta(id, id_cuenta);
        for(IngresoMesDto ingreso : lstIngresos){
            log.info(ingreso.toString());
        }
        response.put("ingresos", lstIngresos);
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Gastos por mes
    @GetMapping("/egresos_por_mes/{id}")
    public ResponseEntity<?> gastosPorMes(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        List<IngresoMesDto> lstEgreso = transaccionService.gastosPorMes(id);
        for(IngresoMesDto ingreso : lstEgreso){
            log.info(ingreso.toString());
        }
        response.put("egresos", lstEgreso);
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/egresos_por_mes/{id}/{id_cuenta}")
    public ResponseEntity<?> gastosPorMes(@PathVariable Integer id, @PathVariable Integer id_cuenta) {
        Map<String, Object> response = new HashMap<>();
        List<IngresoMesDto> lstEgreso = transaccionService.gastosPorMesAndCuenta(id, id_cuenta);
        for(IngresoMesDto ingreso : lstEgreso){
            log.info(ingreso.toString());
        }
        response.put("egresos", lstEgreso);
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
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

    @GetMapping("/transacciones_recientes/{id}/{id_cuenta}")
    public List<TransaccionResponseDto> transaccionesRecientes(@PathVariable Integer id, @PathVariable Integer id_cuenta) {
        List<Transacciones> transacciones = transaccionService.transaccionesRecientesAndCuenta(id, id_cuenta);

        //Creamos una lista de transacciones de respuesta
        List<TransaccionResponseDto> transaccionesResponseList = new ArrayList<>();
        for (Transacciones transaccion : transacciones) {
            transaccionesResponseList.add(setTransaccionResponseDto(transaccion));
        }

        return transaccionesResponseList;
    }

    //Gastos por categoria
    @GetMapping("/gastos_por_categoria/{id}")
    public ResponseEntity<?> gastosPorCategoria(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("gastos", transaccionService.gastosPorCategoria(id));
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/gastos_por_categoria/{id}/{id_cuenta}")
    public ResponseEntity<?> gastosPorCategoria(@PathVariable Integer id, @PathVariable Integer id_cuenta) {
        Map<String, Object> response = new HashMap<>();
        response.put("gastos", transaccionService.gastosPorCategoriaAndCuenta(id, id_cuenta));
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
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
                .numeroCuenta(transaccion.getCuenta().getNumCuenta())
                .build();
    }

}