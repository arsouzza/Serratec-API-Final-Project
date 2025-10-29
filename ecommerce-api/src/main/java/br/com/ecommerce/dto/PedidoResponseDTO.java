package br.com.ecommerce.dto;

import java.time.LocalDateTime;

public class PedidoResponseDTO {
    private Long id;
    private String descricao;
    private Double valor;
    private LocalDateTime dataPedido;
    private Double totalCliente;

    public PedidoResponseDTO(Long id, String descricao, Double valor, LocalDateTime dataPedido, Double totalCliente) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataPedido = dataPedido;
        this.totalCliente = totalCliente;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public Double getValor() { return valor; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public Double getTotalCliente() { return totalCliente; }
}