package utp.edu.codekion.finanzas.controller;

import jakarta.servlet.http.HttpServletResponse;
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
import utp.edu.codekion.finanzas.utils.ReportePdfGenerate;

import java.io.IOException;
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

    // Generar Reporte
    @PostMapping("/generar_reporte")
    public void generarReporte(@RequestBody FechasAndUsuarioDto fechasDto, HttpServletResponse response) {

        // Obtenemos lo necesario para el reporte
        List<Transacciones> transaccionesLst = transaccionService.findByFechaTransaccionBetweenAndUsuario(fechasDto.getFechaInicio(), fechasDto.getFechaFin(),Integer.valueOf(fechasDto.getId_usuario()));
        BigDecimal ingresos = transaccionService.totalIngresosEntreFechas(fechasDto.getFechaInicio(), fechasDto.getFechaFin(), Integer.valueOf(fechasDto.getId_usuario()));
        BigDecimal egresos = transaccionService.totalEgresosEntreFechas(fechasDto.getFechaInicio(), fechasDto.getFechaFin(), Integer.valueOf(fechasDto.getId_usuario()));
        Usuario usuario = usuarioService.findById(Integer.valueOf(fechasDto.getId_usuario()));

        // Creamos el objeto Resumen Transacciones
        ResumenTransacciones resumenTransacciones = new ResumenTransacciones();
        resumenTransacciones.setIdUsuario(usuario);
        resumenTransacciones.setPeriodo(fechasDto.getFechaInicio() + " - " + fechasDto.getFechaFin());
        resumenTransacciones.setTransacciones(convertirTransaccionesAJson(transaccionesLst));
        resumenTransacciones.setTotalIngresos(ingresos);
        resumenTransacciones.setTotalEgresos(egresos);
        ResumenTransacciones save = resumenTransaccionService.save(resumenTransacciones);

        // Creamos el reporte en formato PDF
        byte[] bytesPdf = ReportePdfGenerate.generarReporte(save, transaccionesLst);

        // Configuración de respuesta para que el PDF se descargue automáticamente
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reporte.pdf");
        response.setContentLength(bytesPdf.length);

        try {
            response.getOutputStream().write(bytesPdf);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> convertirTransaccionesAJson(List<Transacciones> transaccionesLst) {
        Map<String, Object> transaccionesMap = new HashMap<>();
        for (Transacciones transaccion : transaccionesLst) {
            transaccionesMap.put(transaccion.getId().toString(), transaccion);
        }
        return transaccionesMap;
    }

}