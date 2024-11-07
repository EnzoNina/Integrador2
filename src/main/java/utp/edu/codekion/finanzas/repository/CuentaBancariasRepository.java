package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utp.edu.codekion.finanzas.model.Cuenta;

import java.util.List;

public interface CuentaBancariasRepository extends JpaRepository<Cuenta, Integer> {

    @Query("SELECT c FROM Cuenta c WHERE c.usuario.id = ?1")
    List<Cuenta> listarCuentasPorUsuario(int idUsuario);
}