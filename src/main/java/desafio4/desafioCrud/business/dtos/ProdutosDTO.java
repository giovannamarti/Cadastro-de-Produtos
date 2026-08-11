package desafio4.desafioCrud.business.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class ProdutosDTO implements Serializable {

    private String nome;
    private String descricao;
    private Double preco;


}
