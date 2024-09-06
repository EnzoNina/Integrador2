package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.TipoTransaccion;

public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion, Integer> {
}