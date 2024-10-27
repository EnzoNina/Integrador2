package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.ResumenTransacciones;
import utp.edu.codekion.finanzas.repository.ResumenTransaccionesRepository;
import utp.edu.codekion.finanzas.service.IService.IResumenTransaccionService;

@Service
@RequiredArgsConstructor
public class ResumenTransaccionServiceImpl implements IResumenTransaccionService {

    private final ResumenTransaccionesRepository resumenTransaccionesRepository;

    @Override
    public ResumenTransacciones save(ResumenTransacciones resumenTransacciones) {
        return resumenTransaccionesRepository.save(resumenTransacciones);
    }
}
