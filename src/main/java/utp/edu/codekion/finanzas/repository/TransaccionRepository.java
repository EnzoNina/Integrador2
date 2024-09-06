package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.Transacciones;

public interface TransaccionRepository extends JpaRepository<Transacciones, Integer> {
}