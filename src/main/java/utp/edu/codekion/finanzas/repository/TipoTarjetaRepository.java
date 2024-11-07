package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.TipoTarjeta;

public interface TipoTarjetaRepository extends JpaRepository<TipoTarjeta, Integer> {
}