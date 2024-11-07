package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.TipoTarjeta;
import utp.edu.codekion.finanzas.repository.TipoTarjetaRepository;
import utp.edu.codekion.finanzas.service.IService.ITipoTarjetaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoTarjetaServiceImpl implements ITipoTarjetaService {

    private final TipoTarjetaRepository tipoTarjetaRepository;

    @Override
    public TipoTarjeta findById(int id) {
        return tipoTarjetaRepository.findById(id).orElse(null);
    }

    @Override
    public List<TipoTarjeta> findAll() {
        return tipoTarjetaRepository.findAll();
    }
}
