package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.codekion.finanzas.model.TipoConcepto;
import utp.edu.codekion.finanzas.service.IService.ITipoConceptoService;

import java.util.List;

@RequestMapping("/concepto")
@RestController
@RequiredArgsConstructor
public class ConceptoController {

    private final ITipoConceptoService tipoConceptoService;

    @GetMapping("/listar")
    public List<TipoConcepto> listAll() {
        return tipoConceptoService.findAll();
    }

    @GetMapping("/{id}")
    public TipoConcepto findById(@PathVariable Integer id) {
        return tipoConceptoService.findById(id);
    }

}
