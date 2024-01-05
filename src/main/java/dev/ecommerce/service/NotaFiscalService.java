package dev.ecommerce.service;

import dev.ecommerce.model.Pedido;

public class NotaFiscalService {

    public void gerar(Pedido pedido){
        System.out.println("Gerando nota fiscal para o pedido " + pedido.getId());
    }
}
