package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.TipoCuenta;
import utp.edu.codekion.finanzas.repository.TipoCuentaRepository;
import utp.edu.codekion.finanzas.service.IService.ITipoCuentaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCuentaServiceImpl implements ITipoCuentaService {

    private final TipoCuentaRepository tipoCuentaRepository;

    @Override
    public TipoCuenta findById(int id) {
        return tipoCuentaRepository.findById(id).orElse(null);
    }

    @Override
    public List<TipoCuenta> findAll() {
        return tipoCuentaRepository.findAll();
    }
}
