package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario> findByUsername(String username);

    Usuario save(Usuario usuario);

}
