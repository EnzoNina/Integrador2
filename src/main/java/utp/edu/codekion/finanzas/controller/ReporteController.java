package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.ResumenTransacciones;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.FechasAndUsuarioDto;
import utp.edu.codekion.finanzas.service.IService.IResumenTransaccionService;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reporte")
@RequiredArgsConstructor
@Log
public class ReporteController {

    private final ITransaccionService transaccionService;
    private final IResumenTransaccionService resumenTransaccionService;
    private final IUsuarioService usuarioService;
    //Generar Reporte
    @PostMapping("/generar_reporte")
    public void generarReporte(@RequestBody FechasAndUsuarioDto fechasDto) {
        //Obtenemos lo necesario para el reporte
        List<Transacciones> transaccionesLst = transaccionService.findByFechaTransaccionBetween(fechasDto.getFechaInicio(), fechasDto.getFechaFin());
        for (Transacciones transaccion : transaccionesLst) {
            log.info("Transaccion: " + transaccion);
        }
        BigDecimal ingresos = transaccionService.totalIngresosEntreFechas(fechasDto.getFechaInicio(), fechasDto.getFechaFin());
        log.info("Ingresos: " + ingresos);
        BigDecimal egresos = transaccionService.totalEgresosEntreFechas(fechasDto.getFechaInicio(), fechasDto.getFechaFin());
        log.info("Egresos: " + egresos);
        Usuario usuario = usuarioService.findById(Integer.valueOf(fechasDto.getId_usuario()));
        log.info("Usuario: " + usuario);
        //Creamos el objeto Resumen Transacciones
        ResumenTransacciones resumenTransacciones = new ResumenTransacciones();
        resumenTransacciones.setIdUsuario(usuario);
        resumenTransacciones.setPeriodo(fechasDto.getFechaInicio() + " - " + fechasDto.getFechaFin());
        resumenTransacciones.setTransacciones(convertirTransaccionesAJson(transaccionesLst));
        resumenTransacciones.setTotalIngresos(ingresos);
        resumenTransacciones.setTotalEgresos(egresos);
        resumenTransaccionService.save(resumenTransacciones);
    }

    private Map<String, Object> convertirTransaccionesAJson(List<Transacciones> transaccionesLst) {
        Map<String, Object> transaccionesMap = new HashMap<>();
        for (Transacciones transaccion : transaccionesLst) {
            transaccionesMap.put(transaccion.getId().toString(), transaccion);
        }
        return transaccionesMap;
    }

}