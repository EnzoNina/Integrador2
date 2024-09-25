package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.*;
import utp.edu.codekion.finanzas.service.IService.ICategoriaService;
import utp.edu.codekion.finanzas.service.IService.IPresupuestoService;
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
    private final ICategoriaService categoriaService;
    private final IUsuarioService usuarioService;

    @PostMapping("/listar/usuario")
    private ResponseEntity<?> listarPresupuestosPorUsuario(@RequestBody UsuarioDto dto) {
        Map<String, Object> response = new HashMap<>();

        Usuario usuario = usuarioService.findById(Integer.valueOf(dto.getId_usuario()));

        List<Presupuesto> listPresupuesto = presupuestoService.listarPresupuestosByUsuario(usuario);
        List<PresupuestoResponseDto> listDto = new ArrayList<>();
        listPresupuesto.forEach(presupuesto -> {
            listDto.add(setAtributosPresupuestoResponseDto(presupuesto));
        });

        response.put("presupuestos", listDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/buscar")
    private ResponseEntity<?> mostrarPresupuesto(@RequestBody FindPresupuestoDto dto) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoria = categoriaService.findById(Integer.valueOf(dto.getId_categoria()));
        Usuario usuario = usuarioService.findById(Integer.valueOf(dto.getId_usuario()));
        Presupuesto presupuesto = presupuestoService.findByCategoriaIdAndUsuario(categoria, usuario);

        if (presupuesto == null) {
            response.put("mensaje", "Presupuesto no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //Creamos el presupuesto response
        PresupuestoResponseDto presupuestoResponseDto = setAtributosPresupuestoResponseDto(presupuesto);

        response.put("mensaje", "Presupuesto encontrado");
        response.put("presupuesto", presupuestoResponseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private PresupuestoResponseDto setAtributosPresupuestoResponseDto(Presupuesto presupuesto) {
        PresupuestoResponseDto presupuestoResponse = new PresupuestoResponseDto();

        presupuestoResponse.setNombre(presupuesto.getNombre());
        presupuestoResponse.setDescripcion(presupuesto.getDescripcion());
        presupuestoResponse.setMonto(String.valueOf(presupuesto.getMonto()));
        presupuestoResponse.setCategoria(presupuesto.getCategoria().getDescripcion());
        presupuestoResponse.setUsuario(presupuesto.getUsuario().getNombres());
        return presupuestoResponse;
    }

    @PostMapping("/guardar")
    private ResponseEntity<?> guardarPresupuesto(@RequestBody PresupuestoDto dto) {

        Map<String, Object> response = new HashMap<>();

        //Buscamos todas las entidades que necesitamos para guardar el presupuesto
        Categoria categoria = categoriaService.findById(Integer.valueOf(dto.getId_categoria()));

        //Buscamos al Usuario
        Usuario usuario = usuarioService.findById(Integer.valueOf(dto.getId_usuario()));

        //Verificamos si el usuario ya tiene un presupuesto en la categoria
        if (presupuestoService.findByCategoriaIdAndUsuario(categoria, usuario) != null) {
            response.put("mensaje", "Ya existe un presupuesto para esta categoría");
            response.put("status", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        // Verificamos que las entidades no sean null
        verificarEntidadNoNula(categoria, "Categoría no existe.");

        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setUsuario(usuario);
        presupuesto.setNombre(dto.getNombre());
        presupuesto.setDescripcion(dto.getDescripcion());
        presupuesto.setMonto(dto.getMonto());
        presupuesto.setCategoria(categoria);

        presupuestoService.save(presupuesto);

        response.put("mensaje", "Presupuesto guardado correctamente");
        response.put("status", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/actualizar/{id}")
    private ResponseEntity<?> actualizarPresupuesto(@RequestBody PresupuestoUpdateDto dto, @PathVariable(name = "id") String id) {

        Map<String, Object> response = new HashMap<>();

        //Buscamos el Presupuesto
        Presupuesto presupuestoEncontrado = presupuestoService.findById(Integer.valueOf(id));

        // Verificamos que las entidades no sean null
        verificarEntidadNoNula(presupuestoEncontrado, "Presupuesto no existe.");

        presupuestoEncontrado.setNombre(dto.getNombre());
        presupuestoEncontrado.setDescripcion(dto.getDescripcion());
        presupuestoEncontrado.setMonto(dto.getMonto());

        presupuestoService.save(presupuestoEncontrado);

        response.put("mensaje", "Presupuesto actualizado correctamente");
        response.put("status", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}