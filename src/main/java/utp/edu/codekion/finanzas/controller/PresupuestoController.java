package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utp.edu.codekion.finanzas.model.Banco;
import utp.edu.codekion.finanzas.model.Cuenta;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.model.dto.*;
import utp.edu.codekion.finanzas.service.IService.ICuentasBancariasService;
import utp.edu.codekion.finanzas.service.IService.IPresupuestoService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioCategoriaService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utp.edu.codekion.finanzas.utils.EntidadNoNulaException.verificarEntidadNoNula;

@RestController
@RequiredArgsConstructor
@RequestMapping("/presupuesto")
public class PresupuestoController {

    private final IPresupuestoService presupuestoService;
    private final IUsuarioService usuarioService;
    private final IUsuarioCategoriaService usuariosCategoriaService;
    private final ICuentasBancariasService cuentaService;

    @PostMapping("/listar/usuario")
    public ResponseEntity<?> listarPresupuestosPorUsuario(@RequestBody RequestPresupuestoListarDto dto) {
        Map<String, Object> response = new HashMap<>();

        Usuario usuario = usuarioService.findById(Integer.valueOf(dto.getId_usuario()));
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(Integer.parseInt(dto.getId_cuenta()));
        List<Presupuesto> listPresupuesto = presupuestoService.listarPresupuestosByUsuario(usuario, cuenta);
        List<PresupuestoResponseDto> listDto = new ArrayList<>();
        listPresupuesto.forEach(presupuesto -> {
            listDto.add(setAtributosPresupuestoResponseDto(presupuesto));
        });

        response.put("presupuestos", listDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/buscar")
    public ResponseEntity<?> mostrarPresupuesto(@RequestBody FindPresupuestoDto dto) {
        Map<String, Object> response = new HashMap<>();

        UsuariosCategoria categoria = usuariosCategoriaService.findById(Integer.valueOf(dto.getId_categoria_usuario()));
        // Cambiar esto
        Presupuesto presupuesto = presupuestoService.findByUsuarioCategoria(categoria);

        if (presupuesto == null) {
            response.put("mensaje", "Presupuesto no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Creamos el presupuesto response
        PresupuestoResponseDto presupuestoResponseDto = setAtributosPresupuestoResponseDto(presupuesto);

        response.put("mensaje", "Presupuesto encontrado");
        response.put("presupuesto", presupuestoResponseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private PresupuestoResponseDto setAtributosPresupuestoResponseDto(Presupuesto presupuesto) {
        PresupuestoResponseDto presupuestoResponse = new PresupuestoResponseDto();
        presupuestoResponse.setId(String.valueOf(presupuesto.getId()));
        presupuestoResponse.setNombre(presupuesto.getNombre());
        presupuestoResponse.setDescripcion(presupuesto.getDescripcion());
        presupuestoResponse.setMonto(String.valueOf(presupuesto.getMonto()));
        presupuestoResponse.setCategoria(presupuesto.getIdCategoria().getIdTipoCat().getDescripcion());
        presupuestoResponse.setUsuario(presupuesto.getIdUsuario().getNombres());
        presupuestoResponse.setNumeroCuenta(presupuesto.getCuenta().getNumCuenta());
        return presupuestoResponse;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPresupuesto(@RequestBody PresupuestoDto dto) {

        Map<String, Object> response = new HashMap<>();

        // Buscamos la categoria
        UsuariosCategoria categoria = usuariosCategoriaService.findById(dto.getId_categoria_usuario());

        // Buscamos al Usuario
        Usuario usuario = usuarioService.findById(dto.getId_usuario());

        Cuenta cuenta = cuentaService.obtenerCuentaPorId(dto.getId_cuenta());

        // El presupuesto solo se puede guardar si la categoria es de tipo egreso
        if (categoria.getIdTipoTra().getId() != 2) {
            response.put("mensaje", "La categoría no es de tipo egreso");
            response.put("status", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            // Verificamos si ya existe un presupuesto para esta categoria y usuario
            if (presupuestoService.findByUsuarioCategoria(categoria) != null) {
                response.put("mensaje", "Ya existe un presupuesto para esta categoría");
                response.put("status", HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Verificamos que las entidades no sean null
            verificarEntidadNoNula(categoria, "Categoría no existe.");
            verificarEntidadNoNula(cuenta, "Cuenta no existe.");

            Presupuesto presupuesto = new Presupuesto();
            presupuesto.setIdUsuario(usuario);
            presupuesto.setNombre(dto.getNombre());
            presupuesto.setDescripcion(dto.getDescripcion());
            presupuesto.setMonto(dto.getMonto());
            presupuesto.setIdCategoria(categoria);
            presupuesto.setCuenta(cuenta);

            presupuestoService.save(presupuesto);

            response.put("mensaje", "Presupuesto guardado correctamente");
            response.put("status", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPresupuesto(@RequestBody PresupuestoUpdateDto dto,
            @PathVariable(name = "id") String id) {

        Map<String, Object> response = new HashMap<>();

        // Buscamos el Presupuesto
        Presupuesto presupuestoEncontrado = presupuestoService.findById(Integer.valueOf(id));

        // Verificamos que las entidades no sean null
        verificarEntidadNoNula(presupuestoEncontrado, "Presupuesto no existe.");

        presupuestoEncontrado.setNombre(dto.getNombre());
        presupuestoEncontrado.setDescripcion(dto.getDescripcion());
        presupuestoEncontrado.setMonto(dto.getMonto());
        presupuestoEncontrado.setCuenta(cuentaService.obtenerCuentaPorId(Integer.parseInt(dto.getId_cuenta())));

        presupuestoService.save(presupuestoEncontrado);

        response.put("mensaje", "Presupuesto actualizado correctamente");
        response.put("status", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}