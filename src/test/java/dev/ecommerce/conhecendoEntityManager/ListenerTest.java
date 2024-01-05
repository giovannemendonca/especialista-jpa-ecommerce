package dev.ecommerce.conhecendoEntityManager;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Cliente;
import dev.ecommerce.model.Pedido;
import dev.ecommerce.model.Produto;
import dev.ecommerce.model.StatusPedido;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListenerTest extends EntityManagerTest {


    @Test
    public void acionarListener() {

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


    @Test
    public void carregarEntidades(){
        Produto produto = entityManager.find(Produto.class, 1);
        Pedido pedido = entityManager.find(Pedido.class, 1);


    }

}
