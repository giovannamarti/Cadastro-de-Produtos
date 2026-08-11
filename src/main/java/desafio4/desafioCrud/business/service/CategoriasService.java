package desafio4.desafioCrud.business.service;

import desafio4.desafioCrud.business.dtos.CategoriasDTO;
import desafio4.desafioCrud.business.mapper.CategoriasConverter;
import desafio4.desafioCrud.business.mapper.ProdutosConverter;
import desafio4.desafioCrud.infrastructure.entities.CategoriasEntity;
import desafio4.desafioCrud.infrastructure.entities.ProdutosEntity;
import desafio4.desafioCrud.infrastructure.exceptions.BusinessException;
import desafio4.desafioCrud.infrastructure.exceptions.ResourceNotFoundException;
import desafio4.desafioCrud.infrastructure.repositories.CategoriasRepository;
import desafio4.desafioCrud.infrastructure.repositories.ProdutosRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.springframework.stereotype.Service;


import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor

public class CategoriasService {


    private final CategoriasRepository categoriasRepository;
    private final ProdutosRepository produtosRepository;
    private final CategoriasConverter categoriasConverter;



    public CategoriasDTO cadastrarCategorias(CategoriasDTO dto) {

        try {
            notNull(dto, "Os dados da categoria são obrigatórios");

            return categoriasConverter.paraCategoriasDTO(categoriasRepository.save(categoriasConverter.paraCategoriasEntity(dto)));
        } catch (Exception e) {
            throw new BusinessException("Erro ao cadastrar a categoria", e);
        }
    }
    public void adicionarCategoriaAProduto(Long categoriaId, Long produtoId) {
        ProdutosEntity produtos = produtosRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        CategoriasEntity categorias = categoriasRepository.findById(categoriaId)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        if (!produtos.getCategorias().contains(categorias)) {
            produtos.getCategorias().add(categorias);
        }
        produtosRepository.save(produtos);

    }
    public CategoriasDTO buscaCategoriasPorId(Long id) {


        CategoriasEntity entity = categoriasRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada " + id));


        return  categoriasConverter.paraCategoriasDTO(entity);

    }
    public List<CategoriasDTO> buscaListaCategorias() {
        List<CategoriasEntity> listaCategorias = categoriasRepository.findAll();

        if (listaCategorias.isEmpty()) {
            throw new ResourceNotFoundException("A lista de categorias está vazia");
        }

        return categoriasConverter.paraListaCategoriasDTO(listaCategorias);
    }
    public CategoriasDTO alteraNomeCategorias(Long id, String nome) {

        CategoriasEntity entity = categoriasRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categoria não encontrada" + id));
        entity.setNomeCategorias(nome);
        categoriasRepository.save(entity);

        return categoriasConverter.paraCategoriasDTO(entity);
    }

    public void deletaCategoriasporId(Long id) {
            CategoriasEntity categoria = categoriasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada: " + id));

            categoriasRepository.delete(categoria);
        }
    }








