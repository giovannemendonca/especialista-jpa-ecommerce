package dev.ecommerce.relacionamentos;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Categoria;
import dev.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class RelacionamentoManyToManyTest extends EntityManagerTest {


    @Test
    public void verificarRelacionamento() {

        Categoria categoria = entityManager.find(Categoria.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setCategorias(Collections.singletonList(categoria));
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());

        Assert.assertFalse(categoriaVerificacao.getProdutos().isEmpty());

    }


}
