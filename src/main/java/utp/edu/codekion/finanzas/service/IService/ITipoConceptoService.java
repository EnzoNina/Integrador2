package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.TipoConcepto;

import java.util.List;

public interface ITipoConceptoService {

    List<TipoConcepto> findAll();

    TipoConcepto findById(Integer id);

}
