package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.dto.PresupuestoDto;
import utp.edu.codekion.finanzas.service.IService.ICategoriaService;
import utp.edu.codekion.finanzas.service.IService.IPresupuestoService;

import java.util.HashMap;
import java.util.Map;

import static utp.edu.codekion.finanzas.utils.EntidadNoNulaException.verificarEntidadNoNula;

@RestController
@RequiredArgsConstructor
@RequestMapping("/presupuesto")
public class PresupuestoController {

    private final IPresupuestoService presupuestoService;
    private final ICategoriaService categoriaService;

    @PostMapping("/guardar")
    private ResponseEntity<?> guardarPresupuesto(@RequestBody PresupuestoDto dto) {

        Map<String, Object> response = new HashMap<>();

        //Buscamos todas las entidades que necesitamos para guardar el presupuesto
        Categoria categoria = categoriaService.findById(dto.getId_categoria());

        //Verificamos si la categoria ya cuenta con un presupuesto
        if (presupuestoService.findByCategoriaId(categoria) != null) {
            response.put("mensaje", "La categoría ya cuenta con un presupuesto");
            response.put("status", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Verificamos que las entidades no sean null
        verificarEntidadNoNula(categoria, "Categoría no existe.");

        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setNombre(dto.getNombre());
        presupuesto.setDescripcion(dto.getDescripcion());
        presupuesto.setMonto(dto.getMonto());
        presupuesto.setCategoria(categoria);

        presupuestoService.save(presupuesto);

        response.put("mensaje", "Presupuesto guardado correctamente");
        response.put("status", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
