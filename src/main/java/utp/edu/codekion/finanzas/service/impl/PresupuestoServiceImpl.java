package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.repository.PresupuestoRepository;
import utp.edu.codekion.finanzas.service.IService.IPresupuestoService;

import java.util.List;

@Service
@RequiredArgsConstructor
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
    public Presupuesto findByUsuarioCategoria(UsuariosCategoria usuariosCategoria) {

        return presupuestoRepository.findByUsuarioCategoria(usuariosCategoria);
    }

    @Override
    public Presupuesto save(Presupuesto presupuesto) {
        return presupuestoRepository.save(presupuesto);
    }
}
