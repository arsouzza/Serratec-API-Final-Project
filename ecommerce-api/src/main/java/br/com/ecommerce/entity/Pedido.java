import br.com.ecommerce.entity.enums.StatusPedido;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double valor;
    private LocalDateTime dataPedido = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pedido() {}

    public Pedido(Long id, String descricao, Double valor, LocalDateTime dataPedido, Cliente cliente) {
        this.id = id; this.descricao = descricao; this.valor = valor; this.dataPedido = dataPedido; this.cliente = cliente;
    }

    public Long getId() { return id; }           public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; } public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValor() { return valor; }   public void setValor(Double valor) { this.valor = valor; }
    public LocalDateTime getDataPedido() { return dataPedido; } public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }
    public Cliente getCliente() { return cliente; } public void setCliente(Cliente cliente) { this.cliente = cliente; }
}