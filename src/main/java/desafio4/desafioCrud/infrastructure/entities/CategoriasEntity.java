package desafio4.desafioCrud.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Data
@Table(name = "CATEGORIAS")
public class CategoriasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nomeCategorias;

    @ManyToMany(mappedBy = "categorias")
    private List<ProdutosEntity> produtos;

}

