import br.com.ecommerce.entity.enums.StatusPedido;

<<<<<<< HEAD
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
=======
import br.com.ecommerce.entity.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime dataCriacao;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<PedidoProduto> pedidoProdutos;
}
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
