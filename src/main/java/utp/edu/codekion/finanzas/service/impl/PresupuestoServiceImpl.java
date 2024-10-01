package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.repository.PresupuestoRepository;
import utp.edu.codekion.finanzas.service.IService.IPresupuestoService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class PresupuestoServiceImpl implements IPresupuestoService {

    private final PresupuestoRepository presupuestoRepository;


    @Override
    public List<Presupuesto> listarPresupuestosByUsuario(Usuario usuario) {
        return presupuestoRepository.listarPresupuestosByCategoriaAndUsuario(usuario);
    }

    @Override
    public Presupuesto findById(Integer id) {
        return presupuestoRepository.findById(id).orElse(null);
    }

    @Override
    public Presupuesto findByCategoriaIdAndUsuario(Categoria categoria, Usuario usuario) {
        log.info("Usuario: " + usuario.getId());
        log.info("Cateo: " + categoria.getId());
        return presupuestoRepository.findByCategoriaAndUsuario(categoria, usuario);
    }

    @Override
    public Presupuesto save(Presupuesto presupuesto) {
        return presupuestoRepository.save(presupuesto);
    }
}
