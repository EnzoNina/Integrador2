package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.Divisa;
import utp.edu.codekion.finanzas.service.IService.IDivisaService;

import java.util.List;

@RequestMapping("/divisa")
@RestController
@RequiredArgsConstructor
public class DivisaController {

    private final IDivisaService divisaService;

    @GetMapping("/listar")
    public List<Divisa> listAll() {
        return divisaService.findAll();
    }

    @GetMapping("/{id}")
    public Divisa findById(@PathVariable Integer id) {
        return divisaService.findById(id);
    }

}