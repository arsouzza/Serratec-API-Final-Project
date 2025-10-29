package br.com.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.ecommerce.dto.PedidoRequestDTO;
import br.com.ecommerce.dto.PedidoResponseDTO;
import br.com.ecommerce.entity.Cliente;
import br.com.ecommerce.entity.Pedido;
import br.com.ecommerce.repository.ClienteRepository;
import br.com.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ClienteRepository clienteRepository;

	public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
		this.pedidoRepository = pedidoRepository;
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	public PedidoResponseDTO criar(PedidoRequestDTO dto) {

		Cliente cliente = clienteRepository.findById(dto.getIdCliente())
				.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + dto.getIdCliente()));

		Pedido pedido = new Pedido();
		pedido.setDescricao(dto.getDescricao());
		pedido.setValor(dto.getValor());
		pedido.setCliente(cliente);
		Pedido salvo = pedidoRepository.save(pedido);

		Double total = pedidoRepository.somaTotalPorCliente(cliente.getId());
		if (total == null)
			total = 0.0;

		return new PedidoResponseDTO(salvo.getId(), salvo.getDescricao(), salvo.getValor(), salvo.getDataPedido(),
				total);
	}
}