package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utp.edu.codekion.finanzas.model.Transacciones;

import java.math.BigDecimal;

public interface TransaccionRepository extends JpaRepository<Transacciones, Integer> {

    @Query("SELECT SUM(t.monto) FROM Transacciones t WHERE t.idCategoria.id = ?1")
    BigDecimal sumarTransaccionesPorCategoria(Integer idCategoria);

}