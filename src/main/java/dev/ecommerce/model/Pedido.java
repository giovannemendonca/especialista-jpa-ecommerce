package dev.ecommerce.model;

import dev.ecommerce.listener.GenericoListener;
import dev.ecommerce.listener.GerarNotaFiscalListener;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@Entity(name = "pedido")
public class Pedido {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamento;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;



    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream()
                    .map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }


    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }


    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir o pedido");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar o pedido");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover o pedido");
    }

    @PostLoad
    public void aposCarregar() {
        System.out.println("Após carregar o pedido");
    }


}
