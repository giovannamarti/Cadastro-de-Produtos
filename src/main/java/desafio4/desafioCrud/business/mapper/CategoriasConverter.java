package desafio4.desafioCrud.business.mapper;

import desafio4.desafioCrud.business.dtos.CategoriasDTO;
import desafio4.desafioCrud.infrastructure.entities.CategoriasEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
    public interface CategoriasConverter {

        CategoriasEntity paraCategoriasEntity(CategoriasDTO dto);

        CategoriasDTO paraCategoriasDTO(CategoriasEntity entity);

    List<CategoriasDTO> paraListaCategoriasDTO(List<CategoriasEntity> entities);


    }
