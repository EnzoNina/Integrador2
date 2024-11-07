package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.service.IService.ITipoTarjetaService;

@RestController
@RequestMapping("/tipo-tarjeta")
@RequiredArgsConstructor
public class TipoTarjetaController {

    private final ITipoTarjetaService tipoTarjetaService;

    //Listar todos los tipos de tarjetas
    @GetMapping("/listar")
    public ResponseEntity<?> listarTiposTarjetas() {
        return ResponseEntity.ok(tipoTarjetaService.findAll());
    }

}
