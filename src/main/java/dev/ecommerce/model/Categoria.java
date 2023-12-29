package dev.ecommerce.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "categoria")
public class Categoria {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    private String nome;

    private Integer categoriaPaiId;
}
