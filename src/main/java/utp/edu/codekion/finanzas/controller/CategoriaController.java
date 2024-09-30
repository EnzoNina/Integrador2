package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.dto.CategoriaDto;
import utp.edu.codekion.finanzas.model.dto.CategoriaResponseDto;
import utp.edu.codekion.finanzas.service.IService.ICategoriaService;
import utp.edu.codekion.finanzas.service.IService.ITipoTransaccionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final ICategoriaService categoriaService;
    private final ITipoTransaccionService tipoTransaccionService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> findById(@PathVariable Integer id) {
        Categoria entity = categoriaService.findById(id);
        CategoriaResponseDto dto = new CategoriaResponseDto();
        dto.setId(String.valueOf(entity.getId()));
        dto.setTransaccion(entity.getIdTipoTra().getDescripcion());
        dto.setDescripcion(entity.getDescripcion());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<CategoriaResponseDto> listAll() {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaResponseDto> responseList = new ArrayList<>();


        list.forEach(categoria -> {
            CategoriaResponseDto dto = new CategoriaResponseDto();
            dto.setId(String.valueOf(categoria.getId()));
            dto.setTransaccion(categoria.getIdTipoTra().getDescripcion());
            dto.setDescripcion(categoria.getDescripcion());
            responseList.add(dto);
        });
        return responseList;
    }

    @PostMapping
    public Categoria save(@RequestBody CategoriaDto dto) {

        Categoria categoria = new Categoria();

        //Buscamos el tipo de transacción
        categoria.setIdTipoTra(tipoTransaccionService.findById(Integer.valueOf(dto.getId_tipo_transaccion())));
        categoria.setDescripcion(dto.getDescripcion());

        return categoriaService.save(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        categoriaService.delete(categoria);
        return new ResponseEntity<>("Categoria eliminada correctamente", HttpStatus.OK);

    }
}
