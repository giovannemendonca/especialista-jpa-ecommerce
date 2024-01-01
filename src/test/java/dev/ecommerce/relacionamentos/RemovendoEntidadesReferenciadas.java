package dev.ecommerce.relacionamentos;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class RemovendoEntidadesReferenciadas extends EntityManagerTest {

    @Test
    public void removerEntidadeReferenciadaTest() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().forEach(itemPedido -> entityManager.remove(itemPedido));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNull(pedidoVerificacao);

    }

}
