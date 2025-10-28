package br.com.ecommerce.dto;

import br.com.ecommerce.entity.ItemPedido;
import java.io.Serializable;

public class ItemPedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProdutoDTO produto;
    private Integer quantidade;
    private Double precoVenda;
    private Double desconto;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(ProdutoDTO produto, Integer quantidade, Double precoVenda, Double desconto) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.desconto = desconto;
    }

    public ItemPedidoDTO(ItemPedido entity) {
        this.produto = new ProdutoDTO(entity.getProduto());
        this.quantidade = entity.getQuantidade();
        this.precoVenda = entity.getPrecoVenda();
        this.desconto = entity.getDesconto();
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
}