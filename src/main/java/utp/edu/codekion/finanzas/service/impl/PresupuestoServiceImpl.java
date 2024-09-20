package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.repository.PresupuestoRepository;
import utp.edu.codekion.finanzas.service.IService.IPresupuestoService;

@Service
@RequiredArgsConstructor
public class PresupuestoServiceImpl implements IPresupuestoService {

    private final PresupuestoRepository presupuestoRepository;

    @Override
    public Presupuesto findByCategoriaId(Categoria categoria) {
        return presupuestoRepository.findByCategoria(categoria);
    }

    @Override
    public Presupuesto save(Presupuesto presupuesto) {
        return presupuestoRepository.save(presupuesto);
    }
}
