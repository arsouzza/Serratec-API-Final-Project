package br.com.ecommerce.dto;

import br.com.ecommerce.entity.Pedido;
import br.com.ecommerce.enums.StatusPedido;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant instante;
    private StatusPedido status;
    private ClienteDTO cliente;
    private List<ItemPedidoDTO> itens = new ArrayList<>();

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, Instant instante, StatusPedido status, ClienteDTO cliente, List<ItemPedidoDTO> itens) {
        this.id = id;
        this.instante = instante;
        this.status = status;
        this.cliente = cliente;
        this.itens = itens;
    }

    public PedidoDTO(Pedido entity) {
        this.id = entity.getId();
        this.instante = entity.getInstante();
        this.status = entity.getStatus();
        this.cliente = new ClienteDTO(entity.getCliente());
        this.itens = entity.getItens().stream()
                .map(ItemPedidoDTO::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstante() {
        return instante;
    }

    public void setInstante(Instant instante) {
        this.instante = instante;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}