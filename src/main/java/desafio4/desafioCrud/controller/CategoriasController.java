package desafio4.desafioCrud.controller;

import desafio4.desafioCrud.business.dtos.CategoriasDTO;
import desafio4.desafioCrud.business.dtos.ProdutosDTO;
import desafio4.desafioCrud.business.service.CategoriasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name= "Categorias", description = "Cadastro das categorias")
public class CategoriasController {

    private final CategoriasService categoriasService;

    @PostMapping
    @Operation(summary = "Salvar Categorias", description = "Cria uma nova categoria")
    @ApiResponse(responseCode = "200",description = "Categoria salva com sucesso")
    @ApiResponse(responseCode = "400",description = "Categoria já cadastrada")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<CategoriasDTO> cadastrarCategorias(@RequestBody CategoriasDTO dto) {
        return ResponseEntity.ok(categoriasService.cadastrarCategorias(dto));
    }

    @PostMapping("/{categoriaId}/produtos/{produtoId}")
    @Operation(summary = "Vincular Produtos a Categorias", description = "Vincula um produto a categoria")
    @ApiResponse(responseCode = "200",description = "Produto vinculado a categoria com sucesso")
    @ApiResponse(responseCode = "400",description = "Produto/Categoria não encontrada")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<Void> vincularProdutos(@PathVariable Long categoriaId, @PathVariable Long produtoId) {
        categoriasService.adicionarCategoriaAProduto(categoriaId, produtoId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    @Operation(summary = "Busca Categorias por Id", description = "Busca categorias")
    @ApiResponse(responseCode = "200",description = "Categoria encontrada com sucesso")
    @ApiResponse(responseCode = "404",description = "Categoria não encontrada")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<CategoriasDTO> buscaCategoriasPorNome(@RequestParam("id") Long id) {
        CategoriasDTO categorias = categoriasService.buscaCategoriasPorId(id);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/todos")
    @Operation(summary = "Busca lista de categorias", description = "Busca todas as categorias")
    @ApiResponse(responseCode = "200",description = "Categorias encontradas")
    @ApiResponse(responseCode = "404",description = "Categorias não encontrada")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<List<CategoriasDTO>> buscaListaCategorias() {
        List<CategoriasDTO> categorias = categoriasService.buscaListaCategorias();
        return ResponseEntity.ok(categorias);

    }

    @PutMapping("/alterar")
    @Operation(summary = "Altera nome de categorias", description = "Altera nome das categorias")
    @ApiResponse(responseCode = "200",description = "Nome da categoria alterado com sucesso")
    @ApiResponse(responseCode = "400",description = "Categoria não pôde ser alterada devido a dados inválidos")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")

    public ResponseEntity<CategoriasDTO> alteraNomeCategorias(@RequestParam("id") Long id,
                                                          @RequestParam("nome") String nome) {
        CategoriasDTO categorias = categoriasService.alteraNomeCategorias(id,nome);
        return ResponseEntity.ok(categorias);

    }


    @DeleteMapping
    @Operation(summary = "Deleta categorias por Id", description = "Deleta categorias")
    @ApiResponse(responseCode = "200",description = "Categoria deletada com sucesso")
    @ApiResponse(responseCode = "404",description = "Categorias não encontrada")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<Void> deletaCategoriasporId(@RequestParam("id") Long id) {
        categoriasService.deletaCategoriasporId(id);
        return ResponseEntity.noContent().build();
    }


}

