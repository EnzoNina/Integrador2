package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.Usuario;

import java.util.List;

public interface IPresupuestoService {

    List<Presupuesto> listarPresupuestosByUsuario(Usuario usuario);

    Presupuesto findById(Integer id);
    Presupuesto findByCategoriaIdAndUsuario(Categoria categoria, Usuario usuario);

    Presupuesto save(Presupuesto presupuesto);

}
