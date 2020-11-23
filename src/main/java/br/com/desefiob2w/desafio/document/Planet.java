package br.com.desefiob2w.desafio.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "planet")
public class Planet {

    @Id
    private String id;
    @NotBlank(message = "Nome não deve ser vazio.")
    private String name;
    @NotBlank(message = "Clima não deve ser vazio.")
    private String climate;
    @NotBlank(message = "Terreno não deve ser vazio.")
    private String terrain;

    private String numFilms;

    public Planet(@NotBlank String name,
                  @NotBlank String climate,
                  @NotBlank String terrain,
                  String numFilms) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.numFilms = numFilms;
    }
}
