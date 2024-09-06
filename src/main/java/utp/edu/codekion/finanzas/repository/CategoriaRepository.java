package utp.edu.codekion.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.codekion.finanzas.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}