package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;

import java.util.List;

public interface UsuariosCategoriaRepository extends JpaRepository<UsuariosCategoria, Integer> {

    @Query("SELECT uc FROM UsuariosCategoria uc WHERE uc.idUsuario = ?1")
    List<UsuariosCategoria> findByUsuario(Usuario usuario);
}