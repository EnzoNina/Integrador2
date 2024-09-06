package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario> findByUsernameAndPassword(String username, String password);

    Optional<Usuario> findByUsername(String username);

    Usuario findById(Integer id);

    Usuario save(Usuario usuario);

}
