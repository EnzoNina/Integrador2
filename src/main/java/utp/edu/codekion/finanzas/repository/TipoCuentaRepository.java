package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.TipoCuenta;

public interface TipoCuentaRepository extends JpaRepository<TipoCuenta, Integer> {
}