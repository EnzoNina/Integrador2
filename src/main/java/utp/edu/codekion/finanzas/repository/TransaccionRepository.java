package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;

import java.math.BigDecimal;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transacciones, Integer> {

    @Query("SELECT SUM(t.monto) FROM Transacciones t WHERE t.idCategoria.id = ?1")
    BigDecimal sumarTransaccionesPorCategoria(Integer idCategoria);

    @Query("SELECT t FROM Transacciones t WHERE t.idUsuario = ?1")
    List<Transacciones> findByUsuario(Usuario usuario);
}