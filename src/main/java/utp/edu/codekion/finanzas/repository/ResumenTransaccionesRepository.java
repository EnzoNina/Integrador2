package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utp.edu.codekion.finanzas.model.ResumenTransacciones;

public interface ResumenTransaccionesRepository extends JpaRepository<ResumenTransacciones, Integer> {

}