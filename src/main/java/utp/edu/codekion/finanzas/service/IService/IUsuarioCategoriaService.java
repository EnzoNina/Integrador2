package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;

import java.util.List;

public interface IUsuarioCategoriaService {

    List<UsuariosCategoria> findByUsuario(Usuario usuario);

    UsuariosCategoria findById(Integer id);

    UsuariosCategoria save(UsuariosCategoria usuariosCategoria);

    void delete(Integer id);

    UsuariosCategoria update(UsuariosCategoria usuariosCategoria);

}