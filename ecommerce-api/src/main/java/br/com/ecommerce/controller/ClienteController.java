package br.com.ecommerce.controller;

import br.com.ecommerce.dto.ClienteDTO;
import br.com.ecommerce.dto.EnderecoDTO;
import br.com.ecommerce.entity.Cliente;
import br.com.ecommerce.entity.Endereco;
import br.com.ecommerce.service.ClienteService;
import br.com.ecommerce.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private final ClienteService clienteService;
	@Autowired
	private final EnderecoService enderecoService;

	public ClienteController(ClienteService clienteService, EnderecoService enderecoService) {
		this.clienteService = clienteService;
		this.enderecoService = enderecoService;
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscar(id));
	}

	@PostMapping
	public ResponseEntity<Cliente> criar(@Valid @RequestBody ClienteDTO dto) {
		return ResponseEntity.ok(clienteService.criar(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
		return ResponseEntity.ok(clienteService.atualizar(id, dto));
	}

	@PostMapping("/{clienteId}/enderecos/cep/{cep}")
	public ResponseEntity<Endereco> criarPorCep(@PathVariable Long clienteId, @PathVariable String cep) {
		Endereco e = clienteService.criarEnderecoPorCep(clienteId, cep);
		return ResponseEntity.ok(e);
	}

	@PostMapping("/{clienteId}/enderecos")
	public ResponseEntity<Endereco> criarEndereco(@PathVariable Long clienteId, @Valid @RequestBody EnderecoDTO dto) {
		Endereco e = new Endereco();
		e.setCep(dto.getCep());
		e.setLogradouro(dto.getLogradouro());
		e.setComplemento(dto.getComplemento());
		e.setBairro(dto.getBairro());
		e.setLocalidade(dto.getLocalidade());
		e.setUf(dto.getUf());
		e.setCliente(clienteService.buscar(clienteId));
		return ResponseEntity.ok(enderecoService.criar(e));
	}
}
