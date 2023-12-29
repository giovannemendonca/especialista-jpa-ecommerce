package dev.ecommerce.mapeamentoBasico;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Cliente;
import dev.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

public class MapeandoEnumeracoesTest  extends EntityManagerTest {

    @Test
    public void testaEnum(){
        Cliente cliente = new Cliente();

        cliente.setNome("José Mineiro");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNotNull(clienteVerificacao);
        Assert.assertEquals(SexoCliente.MASCULINO, clienteVerificacao.getSexo());

    }
}
