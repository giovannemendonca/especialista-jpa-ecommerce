package dev.ecommerce.conhecendoEntityManager;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Pedido;
import dev.ecommerce.model.StatusPedido;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class GerenciandoTransacaoesTest extends EntityManagerTest {


    @Test
    public void abrirFecharCancelarTransacao() {
        assertThrows(Exception.class, () -> erroEsperadoMetodoDeNegocio());
    }


    private void erroEsperadoMetodoDeNegocio() {
        try {
            entityManager.getTransaction().begin();
            metodoDeNegocio();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    private void metodoDeNegocio() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        pedido.setStatus(StatusPedido.PAGO);

        if (pedido.getPagamento() == null) {
            throw new RuntimeException("Pedido ainda não foi pago.");
        }
    }

}
