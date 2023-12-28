package dev.ecommerce.iniciandoComJPA;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    void abrirEFecharTransacao() {

        Produto produto = new Produto();

        // Abrindo uma transação
        entityManager.getTransaction().begin();

        // Executando operações de persistência

        entityManager.persist(produto); // Inserindo um registro
        entityManager.merge(produto); // Atualizando um registro
        entityManager.remove(produto); // Removendo um registro
        entityManager.clear(); // Limpa a memoria do entityManager


        // Fechando uma transação
        entityManager.getTransaction().commit();
    }


    @Test
    public void impedirOperacaoComBancoDeDados() {

        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.detach(produto); // o entityManager deixar de gerenciar

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2ª Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());

        Assert.assertEquals("Kindle", produtoVerificado.getNome());
    }



    @Test
    public void inserirOPrimeiroObjeto() {

        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotNull(produtoVerificado);
        Assert.assertEquals(produtoVerificado.getNome(), produto.getNome());

    }

    @Test
    public void removerObjeto() {

        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificado = entityManager.find(Produto.class, 3);

        Assert.assertNull(produtoVerificado);
    }

    @Test
    public void atualizarObjeto() {

        Produto produto = new Produto();

        produto.setId(1);
        produto.setNome("Kindle PaperWhite");
        produto.setDescricao("Conheça o novo Kindle");
        produto.setPreco(BigDecimal.valueOf(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotNull(produtoVerificado);
        Assert.assertEquals(produtoVerificado.getNome(), produto.getNome());
    }

    @Test
    public void atualizarObjetoGerenciado() {

        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();

        produto.setNome("Kindle Paperwhite 2ª Geração");
        produto.setPreco(BigDecimal.valueOf(599));

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());

        Assert.assertEquals(produtoVerificado.getNome(), produto.getNome());
    }


    @Test
    public void inserirObjetoComMerge() {

        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotNull(produtoVerificado);
        Assert.assertEquals(produtoVerificado.getNome(), produto.getNome());
    }

    @Test
    public void mostrarDiferencaPesistMerge() {

        Produto produtoPesist = new Produto();

        produtoPesist.setId(5);
        produtoPesist.setNome("Smartphone One Plus");
        produtoPesist.setDescricao("O processador mais rápido.");
        produtoPesist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPesist);
        entityManager.getTransaction().commit();
        entityManager.clear();


        Produto produtoVerificadoPersist = entityManager.find(Produto.class, produtoPesist.getId());

        Assert.assertNotNull(produtoVerificadoPersist);
        Assert.assertEquals(produtoVerificadoPersist.getNome(), produtoPesist.getNome());


        Produto produtoMerge = new Produto();

        produtoMerge.setId(6);
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da categoria");
        produtoMerge.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        var produtoGerenciadoMerge = entityManager.merge(produtoMerge);
        produtoGerenciadoMerge.setPreco(BigDecimal.valueOf(2));
        entityManager.getTransaction().commit();
        entityManager.clear();


        Produto produtoVerificadoMerge = entityManager.find(Produto.class, produtoMerge.getId());

        Assert.assertNotNull(produtoVerificadoMerge);
        Assert.assertEquals(produtoVerificadoMerge.getNome(), produtoMerge.getNome());
    }


}
