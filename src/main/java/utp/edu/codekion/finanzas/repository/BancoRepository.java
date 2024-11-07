package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Integer> {
}