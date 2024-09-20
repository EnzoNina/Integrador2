package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.Frecuencia;
import utp.edu.codekion.finanzas.service.IService.IFrecuenciaService;

import java.util.List;

@RequestMapping("/frecuencia")
@RestController
@RequiredArgsConstructor
public class FrecuenciaController {

    private final IFrecuenciaService frecuenciaService;

    @GetMapping("/listar")
    public List<Frecuencia> listAll() {
        return frecuenciaService.findAll();
    }

    @GetMapping("/{id}")
    public Frecuencia findById(@PathVariable Integer id) {
        return frecuenciaService.findById(id);
    }

}
