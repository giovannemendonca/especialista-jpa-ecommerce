package dev.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "nota_fiscal")
public class NotaFiscal {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private Integer pedidoId;

    private String xml;

    private Date dataEmissao;

}
