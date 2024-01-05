package dev.ecommerce.conhecendoEntityManager;

import dev.ecommerce.EntityManagerTest;
import dev.ecommerce.model.Categoria;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    public void analisarEstado(){
        Categoria categoria = new Categoria(); // Estado transitivo

        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1); // Estado gerenciado

        entityManager.remove(categoria); // Estado removido
        entityManager.persist(categoria); // Estado novo

        // o detach deixa de gerenciar o objeto
        entityManager.detach(categoria); // Estado desanexado (desconectado)

    }
}
