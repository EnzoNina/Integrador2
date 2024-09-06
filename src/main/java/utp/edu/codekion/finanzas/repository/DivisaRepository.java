package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.Divisa;

public interface DivisaRepository extends JpaRepository<Divisa, Integer> {
}