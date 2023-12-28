package dev.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "produto")
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private String nome;

    private BigDecimal preco;

    private String descricao;

}
