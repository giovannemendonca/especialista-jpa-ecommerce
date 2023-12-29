package dev.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private LocalDateTime dataPedido;

    private LocalDateTime dataConclusao;

    private Integer notaFiscalId;

    private BigDecimal total;

    private StatusPedido status;

}
