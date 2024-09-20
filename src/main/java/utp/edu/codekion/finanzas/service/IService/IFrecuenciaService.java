package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Frecuencia;

import java.util.List;

public interface IFrecuenciaService {

    List<Frecuencia> findAll();

    Frecuencia findById(Integer id);

}
