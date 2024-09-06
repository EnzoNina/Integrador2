package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.TipoConcepto;

public interface TipoConceptoRepository extends JpaRepository<TipoConcepto, Integer> {
}