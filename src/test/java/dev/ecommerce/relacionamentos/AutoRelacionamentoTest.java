package dev.ecommerce.relacionamentos;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

public class AutoRelacionamentoTest extends EntityManagerTest {


    @Test
    public void verificarRelacionamento() {


        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletronicos");

        Categoria categoria = new Categoria();
        categoria.setNome("Celulares");
        categoria.setCategoriaPai(categoriaPai);


        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());

        Assert.assertNotNull(categoriaVerificacao.getCategoriaPai().getNome());
    }


}
