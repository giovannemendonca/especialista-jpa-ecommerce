package dev.ecommerce.iniciandoComJPA;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador(){

        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assert.assertNotNull(cliente);
        Assert.assertEquals(cliente.getNome(), "Fernando Medeiros");
    }

    @Test
    public void inserirRegistro(){
        Cliente cliente = new Cliente();
        cliente.setNome("Giovanne Mendonça");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var resultado = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertEquals(resultado.getNome(), cliente.getNome());
    }

    @Test
    public void atualizarRegistro(){
        Cliente cliente = new Cliente();
        cliente.setId(2);
        cliente.setNome("Fulano de tal");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var resultado = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertEquals(resultado.getNome(), cliente.getNome());
    }

    @Test
    public void removerRegistro(){

        Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        var resultado = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNull(resultado);
    }
}
