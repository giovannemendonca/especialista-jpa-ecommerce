package dev.ecommerce.listener;

import dev.ecommerce.model.Pedido;
import dev.ecommerce.service.NotaFiscalService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private final NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PreUpdate
    @PrePersist
    public void gerar(Pedido pedido){

        if(pedido.isPago() && pedido.getNotaFiscal() == null){
            notaFiscalService.gerar(pedido);
        }

    }
}
