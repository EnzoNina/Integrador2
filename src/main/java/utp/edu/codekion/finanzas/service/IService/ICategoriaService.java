package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Categoria;

import java.util.List;

public interface ICategoriaService {

    List<Categoria> findAll();

    Categoria findById(Integer id);

    Categoria save(Categoria categoria);

    void delete(Categoria categoria);

}
