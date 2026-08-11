package desafio4.desafioCrud.infrastructure.repositories;

import desafio4.desafioCrud.infrastructure.entities.ProdutosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface ProdutosRepository extends JpaRepository<ProdutosEntity, Long> {
    }

