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
public class CategoriasDTO implements Serializable {


    private String nomeCategorias;
}