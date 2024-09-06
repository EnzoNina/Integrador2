package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.Frecuencia;

public interface FrecuenciaRepository extends JpaRepository<Frecuencia, Integer> {
}