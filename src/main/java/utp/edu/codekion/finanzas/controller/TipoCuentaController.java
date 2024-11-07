package utp.edu.codekion.finanzas.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.TipoCuenta;
import utp.edu.codekion.finanzas.service.IService.ITipoCuentaService;

import java.util.List;

@RestController
@RequestMapping("/tipo-cuenta")
@RequiredArgsConstructor
public class TipoCuentaController {

    private final ITipoCuentaService tipoCuentaService;

    //Listar todos los tipo cuenta
    @GetMapping("/listar")
    public ResponseEntity<?> listarTipoCuenta() {
        List<TipoCuenta> listaTipoCuenta = tipoCuentaService.findAll();
        return ResponseEntity.ok(listaTipoCuenta);
    }

}
