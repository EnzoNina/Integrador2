package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;

import java.util.List;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {



    @Query("SELECT p FROM Presupuesto p WHERE p.idUsuario = ?1")
    List<Presupuesto> listarPresupuestosByCategoriaAndUsuario(Usuario usuario);

    @Query("SELECT p FROM Presupuesto p WHERE p.idCategoria = ?1")
    Presupuesto findByUsuarioCategoria(UsuariosCategoria usuariosCategoria);
}