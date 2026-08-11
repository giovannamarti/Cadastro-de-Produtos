package desafio4.desafioCrud.business.mapper;

import desafio4.desafioCrud.business.dtos.ProdutosDTO;
import desafio4.desafioCrud.infrastructure.entities.ProdutosEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutosConverter {

        ProdutosEntity paraProdutosEntity(ProdutosDTO dto);

        ProdutosDTO paraProdutosDTO(ProdutosEntity entity);

    List<ProdutosDTO> paraListaProdutosDTO(List<ProdutosEntity> entities);

    }
