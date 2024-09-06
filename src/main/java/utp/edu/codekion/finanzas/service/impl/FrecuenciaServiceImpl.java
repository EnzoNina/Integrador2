package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Frecuencia;
import utp.edu.codekion.finanzas.repository.FrecuenciaRepository;
import utp.edu.codekion.finanzas.service.IService.IFrecuenciaService;

@Service
@RequiredArgsConstructor
public class FrecuenciaServiceImpl implements IFrecuenciaService {

    private final FrecuenciaRepository frecuenciaRepository;

    @Override
    public Frecuencia findById(Integer id) {
        return frecuenciaRepository.findById(id).orElse(null);
    }
}
