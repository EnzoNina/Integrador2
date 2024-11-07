package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.codekion.finanzas.model.*;
import utp.edu.codekion.finanzas.model.dto.TransaccionDto;
import utp.edu.codekion.finanzas.model.dto.TransaccionResponseDto;
import utp.edu.codekion.finanzas.model.dto.UsuarioDto;
import utp.edu.codekion.finanzas.service.IService.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utp.edu.codekion.finanzas.utils.EntidadNoNulaException.verificarEntidadNoNula;

@RestController
@RequestMapping("/transaccion")
@RequiredArgsConstructor
@Log
public class TransaccionController {

    private final Map<String, Object> response = new HashMap<>();
    private final ITransaccionService transaccionService;
    private final IUsuarioService usuarioService;
    private final IUsuarioCategoriaService usuarioCategoriaService;
    private final ITipoConceptoService tipoConceptoService;
    private final IFrecuenciaService frecuenciaService;
    private final IDivisaService divisaService;
    private final IPresupuestoService presupuestoService;

    @PostMapping("/listar")
    public ResponseEntity<?> listarTransacciones(@RequestBody UsuarioDto dto) {
        response.clear();
        Usuario usuario = usuarioService.findById(Integer.valueOf(dto.getId_usuario()));
        List<Transacciones> transaccionesListUsuario = transaccionService.findByUsuario(usuario);

        //Creamos una lista de transacciones de respuesta
        List<TransaccionResponseDto> transaccionesResponseList = new ArrayList<>();
        for (Transacciones transaccion : transaccionesListUsuario) {
            transaccionesResponseList.add(setTransaccionResponseDto(transaccion));
        }

        response.put("transacciones", transaccionesResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTransaccion(@PathVariable Integer id) {
        response.clear();
        Transacciones transaccion = transaccionService.findById(id);
        if (transaccion == null) {
            response.put("mensaje", "Transacción no encontrada");
            response.put("status", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        //Creamos el objeto de respuesta
        TransaccionResponseDto transaccionResponseDto = setTransaccionResponseDto(transaccion);


        response.put("transaccion", transaccionResponseDto);
        response.put("status", HttpStatus.OK);
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
                .build();
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTransaccion(@RequestBody TransaccionDto dto) {
        response.clear();
        //Buscamos la categoria y el usuario
        UsuariosCategoria categoria = usuarioCategoriaService.findById(Integer.valueOf(dto.getId_tipo_categoria()));
        log.info("Categoria: " + categoria);
        Usuario usuario = usuarioService.findById(Integer.valueOf(dto.getId_usuario()));
        log.info("Usuario: " + usuario);

        //Verificamos que la transacción sea de un egreso
        if (categoria.getIdTipoTra().getId() == 2) {
            //Buscamos si la categoria de la transacción tiene un presupuesto
            Presupuesto presupuestoEncontrado = presupuestoService.findByUsuarioCategoria(categoria);
            log.info("Presupuesto: " + presupuestoEncontrado);
            if (presupuestoEncontrado != null) {
                //Sumar todas las transacciones de una categoria y comparar con el presupuesto
                BigDecimal totalTransacciones = transaccionService.sumarTransaccionesPorCategoriaAndUsuario(categoria.getId(), usuario);

                if (totalTransacciones == null) {
                    totalTransacciones = BigDecimal.ZERO;
                }

                //Comprobamos si el total de las transacciones supera el presupuesto
                if (totalTransacciones.add(dto.getMonto()).compareTo(presupuestoEncontrado.getMonto()) > 0) {
                    response.put("mensaje", "El monto de la transacción supera el presupuesto");
                    response.put("status", HttpStatus.BAD_REQUEST);
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
            }
        }

        //Buscamos todas las entidades que necesitamos para guardar la transaccion
        TipoConcepto tipoConcepto = tipoConceptoService.findById(Integer.valueOf(dto.getId_tipo_concepto()));
        Frecuencia frecuencia = frecuenciaService.findById(Integer.valueOf(dto.getId_tipo_frecuencia()));
        Divisa divisa = divisaService.findById(Integer.valueOf(dto.getId_tipo_divisa()));

        // Verificamos que las entidades no sean null
        verificarEntidadNoNula(usuario, "Usuario no existe");
        verificarEntidadNoNula(categoria, "Categoría no existe");
        verificarEntidadNoNula(tipoConcepto, "Tipo de concepto no existe");
        verificarEntidadNoNula(frecuencia, "Frecuencia no existe");
        verificarEntidadNoNula(divisa, "Divisa no existe");

        //Una vez que tenemos todas las entidades, creamos la transaccion
        Transacciones transaccion = Transacciones.builder()
                .idUsuario(usuario)
                .idCategoria(categoria)
                .idConcepto(tipoConcepto)
                .idFrecuencia(frecuencia)
                .monto(dto.getMonto())
                .divisa(divisa)
                .descripcion(dto.getDescripcion())
                .build();

        //Guardamos la transaccion
        transaccionService.save(transaccion);
        response.put("mensaje", "Transacción guardada correctamente");
        response.put("status", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}