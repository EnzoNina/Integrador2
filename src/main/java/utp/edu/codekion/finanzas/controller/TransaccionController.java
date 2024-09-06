package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.*;
import utp.edu.codekion.finanzas.model.dto.TransaccionDto;
import utp.edu.codekion.finanzas.service.IService.*;

import java.util.HashMap;
import java.util.Map;

import static utp.edu.codekion.finanzas.utils.EntidadNoNulaException.verificarEntidadNoNula;

@RestController
@RequestMapping("/transaccion")
@RequiredArgsConstructor
public class TransaccionController {

    private final Map<String, Object> response = new HashMap<>();

    private final ITransaccionService transaccionService;
    private final IUsuarioService usuarioService;
    private final ITipoTransaccionService tipoTransaccionService;
    private final ICategoriaService categoriaService;
    private final ITipoConceptoService tipoConceptoService;
    private final IFrecuenciaService frecuenciaService;
    private final IDivisaService divisaService;

    @PostMapping("/guardar")
    private ResponseEntity<?> guardarTransaccion(@RequestBody TransaccionDto dto) {
        response.clear();
        //Buscamos todas las entidades que necesitamos para guardar la transaccion
        Usuario usuario = usuarioService.findById(Integer.valueOf(dto.getId_usuario()));
        TipoTransaccion tipoTransaccion = tipoTransaccionService.findById(Integer.valueOf(dto.getId_tipo_transaccion()));
        Categoria categoria = categoriaService.findById(Integer.valueOf(dto.getId_tipo_categoria()));
        TipoConcepto tipoConcepto = tipoConceptoService.findById(Integer.valueOf(dto.getId_tipo_concepto()));
        Frecuencia frecuencia = frecuenciaService.findById(Integer.valueOf(dto.getId_tipo_frecuencia()));
        Divisa divisa = divisaService.findById(Integer.valueOf(dto.getId_tipo_divisa()));

        // Verificamos que las entidades no sean null
        verificarEntidadNoNula(usuario, "Usuario no existe");
        verificarEntidadNoNula(tipoTransaccion, "Tipo de transacción no existe");
        verificarEntidadNoNula(categoria, "Categoría no existe");
        verificarEntidadNoNula(tipoConcepto, "Tipo de concepto no existe");
        verificarEntidadNoNula(frecuencia, "Frecuencia no existe");
        verificarEntidadNoNula(divisa, "Divisa no existe");

        //Una vez que tenemos todas las entidades, creamos la transaccion
        Transacciones transaccion = Transacciones.builder()
                .idUsuario(usuario)
                .idCategoria(categoria)
                .idTipoTra(tipoTransaccion)
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