package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.TipoConcepto;
import utp.edu.codekion.finanzas.repository.TipoConceptoRepository;
import utp.edu.codekion.finanzas.service.IService.ITipoConceptoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoConceptoServiceImpl implements ITipoConceptoService {

    private final TipoConceptoRepository tipoConceptoRepository;


    @Override
    public List<TipoConcepto> findAll() {
        return tipoConceptoRepository.findAll();
    }

    @Override
    public TipoConcepto findById(Integer id) {
        return tipoConceptoRepository.findById(id).orElse(null);
    }
}