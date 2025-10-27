package br.com.ecommerce.entity;

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
