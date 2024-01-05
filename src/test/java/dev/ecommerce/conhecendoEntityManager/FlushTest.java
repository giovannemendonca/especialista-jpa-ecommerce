package dev.ecommerce.conhecendoEntityManager;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Pedido;
import dev.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

public class FlushTest extends EntityManagerTest {

    @Test
    public void chamarFlush(){
        Assert.assertThrows(Exception.class,() -> erroAoChamarFlush());
    }

    private void erroAoChamarFlush() {
        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

            entityManager.flush(); // o flush sincroniza o que está na memória com o banco de dados

            if (pedido.getPagamento() == null) {
                throw new RuntimeException("Pedido ainda não foi pago.");
            }

//            Uma consulta obriga o JPA a sincronizar o que ele tem na memória (sem usar o flush explicitamente).
//            Pedido pedidoPago = entityManager
//                    .createQuery("select p from Pedido p where p.id = 1", Pedido.class)
//                    .getSingleResult();
//            Assertions.assertEquals(pedido.getStatus(), pedidoPago.getStatus());

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
