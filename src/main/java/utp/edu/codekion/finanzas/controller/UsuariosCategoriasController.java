package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.TipoTransaccion;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.model.dto.CategoriaRequestDto;
import utp.edu.codekion.finanzas.model.dto.UsuariosCategoriasRequestDto;
import utp.edu.codekion.finanzas.model.dto.UsuariosCategoriasResponseDto;
import utp.edu.codekion.finanzas.service.IService.ICategoriaService;
import utp.edu.codekion.finanzas.service.IService.ITipoTransaccionService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioCategoriaService;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class UsuariosCategoriasController {

    private final IUsuarioCategoriaService usuarioCategoriaService;
    private final IUsuarioService usuarioService;
    private final ITipoTransaccionService tipoTransaccionService;
    private final ICategoriaService categoriaService;



    @GetMapping("/{id}")
    public ResponseEntity<UsuariosCategoriasResponseDto> findById(@PathVariable Integer id) {
        UsuariosCategoria entity = usuarioCategoriaService.findById(id);
        UsuariosCategoriasResponseDto dto = new UsuariosCategoriasResponseDto();
        dto.setId(String.valueOf(entity.getId()));
        dto.setCategoria(entity.getIdTipoCat().getDescripcion());
        dto.setTipo_transaccion(entity.getIdTipoTra().getDescripcion());
        dto.setDescripcion(entity.getDescripcion());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/listar/usuario/{id}")
    public List<UsuariosCategoriasResponseDto> listAll(@PathVariable(name = "id") Integer id) {

        //Buscamos al usuario
        Usuario usuario = usuarioService.findById(id);

        List<UsuariosCategoria> list = usuarioCategoriaService.findByUsuario(usuario);

        List<UsuariosCategoriasResponseDto> responseList = new ArrayList<>();

        list.forEach(categoria -> {
            UsuariosCategoriasResponseDto dto = new UsuariosCategoriasResponseDto();
            dto.setId(String.valueOf(categoria.getId()));
            dto.setCategoria(categoria.getIdTipoCat().getDescripcion());
            dto.setTipo_transaccion(categoria.getIdTipoTra().getDescripcion());
            dto.setDescripcion(categoria.getDescripcion());
            responseList.add(dto);
        });
        return responseList;
    }



    @PostMapping("/crearCategoriaUsuario")
    public UsuariosCategoriasResponseDto save(@RequestBody UsuariosCategoriasRequestDto dto) {

        //Buscamos las entidades
        Usuario usuario = usuarioService.findById(Integer.parseInt(dto.getId_usuario()));
        TipoTransaccion tipoTransaccion = tipoTransaccionService.findById(Integer.parseInt(dto.getId_tipo_transaccion()));
        Categoria categoriaEncontrada = categoriaService.findById(Integer.valueOf(dto.getId_categoria()));

        UsuariosCategoria usuarioCategoria = new UsuariosCategoria();
        usuarioCategoria.setIdUsuario(usuario);
        usuarioCategoria.setIdTipoTra(tipoTransaccion);
        usuarioCategoria.setIdTipoCat(categoriaEncontrada);
        usuarioCategoria.setDescripcion(dto.getDescripcion());

        UsuariosCategoria save = usuarioCategoriaService.save(usuarioCategoria);

        UsuariosCategoriasResponseDto response = new UsuariosCategoriasResponseDto();
        response.setId(String.valueOf(save.getId()));
        response.setCategoria(save.getIdTipoCat().getDescripcion());
        response.setTipo_transaccion(save.getIdTipoTra().getDescripcion());
        response.setDescripcion(save.getDescripcion());
        return response;
    }

    @PutMapping("/actualizar/{id}")
    public UsuariosCategoria update(@PathVariable Integer id, @RequestBody UsuariosCategoriasRequestDto dto) {

        //Buscamos
        TipoTransaccion tipoTransaccion = tipoTransaccionService.findById(Integer.parseInt(dto.getId_tipo_transaccion()));
        Categoria categoriaEncontrada = categoriaService.findById(Integer.valueOf(dto.getId_categoria()));
        Usuario usuario = usuarioService.findById(Integer.parseInt(dto.getId_usuario()));

        UsuariosCategoria usuarioCategoria = usuarioCategoriaService.findById(id);
        usuarioCategoria.setIdUsuario(usuario);
        usuarioCategoria.setIdTipoCat(categoriaEncontrada);
        usuarioCategoria.setDescripcion(dto.getDescripcion());
        usuarioCategoria.setIdTipoTra(tipoTransaccion);
        return usuarioCategoriaService.update(usuarioCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        usuarioCategoriaService.delete(id);
        response.put("mensaje", "Categoria eliminada correctamente");
        return ResponseEntity.ok(response);

    }

}