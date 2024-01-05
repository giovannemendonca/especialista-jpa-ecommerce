package dev.ecommerce.conhecendoEntityManager;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Cliente;
import dev.ecommerce.model.Pedido;
import dev.ecommerce.model.StatusPedido;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CallbacksTest extends EntityManagerTest {


    @Test
    public void acionarCallbacks() {

        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        assertEquals(StatusPedido.PAGO, pedidoVerificacao.getStatus());
    }

}
