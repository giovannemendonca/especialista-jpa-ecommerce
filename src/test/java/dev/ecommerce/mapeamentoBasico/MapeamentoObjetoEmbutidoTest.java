package dev.ecommerce.mapeamentoBasico;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.EnderecoEntregaPedido;
import dev.ecommerce.model.Pedido;
import dev.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void analisarMapementoObjetoEmbutido() {

        EnderecoEntregaPedido enderecoEntregaPedido = new EnderecoEntregaPedido();
        enderecoEntregaPedido.setCep("00000-000");
        enderecoEntregaPedido.setLogradouro("Rua das Laranjeiras");
        enderecoEntregaPedido.setNumero("123");
        enderecoEntregaPedido.setBairro("Centro");
        enderecoEntregaPedido.setCidade("Uberlândia");
        enderecoEntregaPedido.setComplemento("Casa");

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setTotal(BigDecimal.valueOf(100.0));
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setEnderecoEntrega(enderecoEntregaPedido);


        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertEquals("Uberlândia", pedidoVerificacao.getEnderecoEntrega().getCidade());
        Assert.assertEquals("00000-000", pedidoVerificacao.getEnderecoEntrega().getCep());
    }

}
