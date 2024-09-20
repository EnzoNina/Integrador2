package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;

public interface IPresupuestoService {

    Presupuesto findByCategoriaId(Categoria categoria);

    Presupuesto save(Presupuesto presupuesto);

}
