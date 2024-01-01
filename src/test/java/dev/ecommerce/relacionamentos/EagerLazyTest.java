package dev.ecommerce.relacionamentos;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Pedido;
import org.junit.Test;

public class EagerLazyTest extends EntityManagerTest {

    @Test
    public void verificandoComportamento(){

        Pedido pedido = entityManager.find(Pedido.class, 1);

        // pedido.getItens().isEmpty();

    }
}
