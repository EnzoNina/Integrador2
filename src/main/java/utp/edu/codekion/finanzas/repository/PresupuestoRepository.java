package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.Presupuesto;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {

    @Query("SELECT p FROM Presupuesto p WHERE p.categoria = ?1")
    Presupuesto findByCategoria(Categoria categoria);


}