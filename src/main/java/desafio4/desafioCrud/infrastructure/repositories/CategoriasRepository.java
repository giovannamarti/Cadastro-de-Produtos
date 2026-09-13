package desafio4.desafioCrud.infrastructure.repositories;

import desafio4.desafioCrud.infrastructure.entities.CategoriasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasRepository extends JpaRepository<CategoriasEntity, Long> {
    boolean existsBynomeCategoriasIgnoreCase(String nome);
}
