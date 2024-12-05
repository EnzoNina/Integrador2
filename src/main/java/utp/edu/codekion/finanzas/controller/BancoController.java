package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.Banco;
import utp.edu.codekion.finanzas.service.IService.IBancoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banco")
public class BancoController {

    private final IBancoService bancoService;

    @GetMapping("/get")
    List<Banco> findAll(){
        return bancoService.findAll();
    }

}