package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.TipoTransaccion;
import utp.edu.codekion.finanzas.repository.TipoTransaccionRepository;
import utp.edu.codekion.finanzas.service.IService.ITipoTransaccionService;

@Service
@RequiredArgsConstructor
public class TipoTransaccionServiceImpl implements ITipoTransaccionService {

    private final TipoTransaccionRepository tipoTransaccionRepository;

    @Override
    public TipoTransaccion findById(Integer id) {
        return tipoTransaccionRepository.findById(id).orElse(null);
    }
}