package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.dto.CategoriaRequestDto;
import utp.edu.codekion.finanzas.service.IService.ICategoriaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriasController {

    private final ICategoriaService categoriaService;

    @GetMapping("/listarCategorias")
    public List<Categoria> listarCategorias() {
        return categoriaService.findAll();
    }

    @PostMapping("/crearCategoria")
    public ResponseEntity<?> crearCategoria(@RequestBody CategoriaRequestDto dto) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoria = new Categoria();
        categoria.setDescripcion(dto.getDescripcion());
        //Guardamos la categoria
        Categoria save = categoriaService.save(categoria);
        if (save != null) {
            response.put("mensaje", "Categoria creada correctamente");
            response.put("categoria", save);
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", "Error al crear la categoria");
            return ResponseEntity.badRequest().body(response);
        }

    }
}
