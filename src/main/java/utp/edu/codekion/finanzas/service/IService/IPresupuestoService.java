package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;

import java.util.List;

public interface IPresupuestoService {

    List<Presupuesto> listarPresupuestosByUsuario(Usuario usuario);

    Presupuesto findById(Integer id);

    Presupuesto findByUsuarioCategoria(UsuariosCategoria usuariosCategoria);

    Presupuesto save(Presupuesto presupuesto);

}
