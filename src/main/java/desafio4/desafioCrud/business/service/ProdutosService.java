package desafio4.desafioCrud.business.service;


import desafio4.desafioCrud.business.dtos.ProdutosDTO;
import desafio4.desafioCrud.business.mapper.ProdutosConverter;
import desafio4.desafioCrud.infrastructure.entities.ProdutosEntity;
import desafio4.desafioCrud.infrastructure.exceptions.BusinessException;
import desafio4.desafioCrud.infrastructure.exceptions.ConflictException;
import desafio4.desafioCrud.infrastructure.exceptions.ResourceNotFoundException;
import desafio4.desafioCrud.infrastructure.repositories.ProdutosRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.Assert.notNull;

    @Service
    @RequiredArgsConstructor

    public class ProdutosService {


        private final ProdutosRepository produtosRepository;
        private final ProdutosConverter produtosConverter;


        public ProdutosDTO cadastrarProdutos(ProdutosDTO dto) {
            if (produtosRepository.existsByNomeIgnoreCase(dto.getNome())){
                throw new ConflictException("Esse produto já está cadastrado");
            }
            try {
                notNull(dto, "Os dados do produto são obrigatórios");
                return produtosConverter.paraProdutosDTO(produtosRepository.save(produtosConverter.paraProdutosEntity(dto)));

            } catch (Exception e) {
                throw new BusinessException("Erro ao cadastrar o produto", e);
            }

        }

        public ProdutosDTO buscaProdutosPorId(Long id) {


            ProdutosEntity entity = produtosRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Produto não encontrado " + id));


            return produtosConverter.paraProdutosDTO(entity);

        }

        public List<ProdutosDTO> buscaListaProdutos() {
            List<ProdutosEntity> listaProdutos = produtosRepository.findAll();

            if (listaProdutos.isEmpty()) {
                throw new ResourceNotFoundException("A lista de produtos está vazia");
            }

            return produtosConverter.paraListaProdutosDTO(listaProdutos);
        }

        public ProdutosDTO alteraProduto(Long id, ProdutosDTO dto) {

            ProdutosEntity entity = produtosRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Produto não encontrado" + id));
            entity.setNome(dto.getNome());
            entity.setDescricao(dto.getDescricao());
            entity.setPreco(dto.getPreco());
            produtosRepository.save(entity);

            return produtosConverter.paraProdutosDTO(entity);
        }

        public void deletaProdutosporId(Long id) {
            ProdutosEntity produtos = produtosRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));
            produtosRepository.deleteById(id);
        }


    }

