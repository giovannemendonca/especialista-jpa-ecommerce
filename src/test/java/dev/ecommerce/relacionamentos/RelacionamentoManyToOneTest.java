package dev.ecommerce.relacionamentos;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Cliente;
import dev.ecommerce.model.Pedido;
import dev.ecommerce.model.StatusPedido;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoManyToOneTest extends EntityManagerTest {


    @Test
    public void verificarRelacionamento() {

        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(BigDecimal.valueOf(10));
        pedido.setDataPedido(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        System.out.println("Pedido: " + pedidoVerificacao.getCliente().getNome());
        

    }

}
