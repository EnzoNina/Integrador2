package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Frecuencia;
import utp.edu.codekion.finanzas.repository.FrecuenciaRepository;
import utp.edu.codekion.finanzas.service.IService.IFrecuenciaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FrecuenciaServiceImpl implements IFrecuenciaService {

    private final FrecuenciaRepository frecuenciaRepository;

    @Override
    public List<Frecuencia> findAll() {
        return frecuenciaRepository.findAll();
    }

    @Override
    public Frecuencia findById(Integer id) {
        return frecuenciaRepository.findById(id).orElse(null);
    }
}
