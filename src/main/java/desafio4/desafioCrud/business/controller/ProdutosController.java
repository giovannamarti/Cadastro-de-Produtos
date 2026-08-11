package desafio4.desafioCrud.business.controller;
import desafio4.desafioCrud.business.dtos.ProdutosDTO;
import desafio4.desafioCrud.business.service.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name= "Produtos", description = "Cadastro dos produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    @PostMapping
    @Operation(summary = "Salvar Produtos", description = "Salva um novo produtp")
    @ApiResponse(responseCode = "200",description = "Produto salvo com sucesso")
    @ApiResponse(responseCode = "400",description = "Produto já cadastrada")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<ProdutosDTO> cadastrarProdutos(@RequestBody ProdutosDTO dto) {

        String Nome = dto.getNome();
        return ResponseEntity.ok(produtosService.cadastrarProdutos(dto));
    }


    @GetMapping
    @Operation(summary = "Busca Produtos por Id", description = "Busca produtos")
    @ApiResponse(responseCode = "200",description = "Produto encontrado com sucesso")
    @ApiResponse(responseCode = "404",description = "Produto não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<ProdutosDTO> buscaProdutosPorId(@RequestParam("id") Long id) {
        ProdutosDTO produtos = produtosService.buscaProdutosPorId(id);
        return ResponseEntity.ok(produtos);
    }


    @GetMapping("/todos")
    @Operation(summary = "Busca lista de produtos", description = "Busca todos os produtos")
    @ApiResponse(responseCode = "200",description = "Produtos encontrados")
    @ApiResponse(responseCode = "404",description = "Produtos não encontrados")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<List<ProdutosDTO>> buscaListaProdutos() {
        List<ProdutosDTO> produtos = produtosService.buscaListaProdutos();
        return ResponseEntity.ok(produtos);

    }

    @PutMapping("/alterar")
    @Operation(summary = "Altera dados do produto", description = "Altera produto")
    @ApiResponse(responseCode = "200",description = "Dados do produto alterado com sucesso")
    @ApiResponse(responseCode = "400",description = "Produto não pôde ser alterado devido a dados inválidos")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<ProdutosDTO> alteraProduto (@RequestParam Long id, @RequestBody ProdutosDTO dto)
                                                           {
        ProdutosDTO produtos = produtosService.alteraProduto(id, dto);
        return ResponseEntity.ok(produtos);

    }

    @DeleteMapping
    @Operation(summary = "Deleta produtos por Id", description = "Deleta produtos")
    @ApiResponse(responseCode = "200",description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = "404",description = "Produto não encontrada")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<Void> deletaProdutosporId (@RequestParam("id") Long id){
        produtosService.deletaProdutosporId(id);
        return ResponseEntity.noContent().build();

        }
    }


