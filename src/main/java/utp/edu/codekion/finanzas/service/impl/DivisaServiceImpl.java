package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Divisa;
import utp.edu.codekion.finanzas.repository.DivisaRepository;
import utp.edu.codekion.finanzas.service.IService.IDivisaService;

@RequiredArgsConstructor
@Service
public class DivisaServiceImpl implements IDivisaService {

    private final DivisaRepository divisaRepository;

    @Override
    public Divisa findById(Integer id) {
        return divisaRepository.findById(id).orElse(null);
    }

}