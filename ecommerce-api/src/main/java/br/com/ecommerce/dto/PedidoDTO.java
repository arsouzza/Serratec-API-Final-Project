package br.com.ecommerce.dto;

import br.com.ecommerce.entity.Pedido;
import br.com.ecommerce.enums.StatusPedido;

    private String descricao;
    private Double valor;

    public PedidoDTO() {}

    public PedidoDTO(String descricao, Double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

