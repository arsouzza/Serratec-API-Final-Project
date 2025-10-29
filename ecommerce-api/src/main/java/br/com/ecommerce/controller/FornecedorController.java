package br.com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ecommerce.dto.FornecedorRequestDTO;
import br.com.ecommerce.dto.FornecedorResponseDTO;
import br.com.ecommerce.service.FornecedorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/fornecedores")
public class FornecedorController {

	private final FornecedorService fornecedorService;

	@Autowired
	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@PostMapping
	public ResponseEntity<FornecedorResponseDTO> inserir(@Valid @RequestBody FornecedorRequestDTO dto) {
		FornecedorResponseDTO response = fornecedorService.inserir(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FornecedorResponseDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody FornecedorRequestDTO dto) {
		return ResponseEntity.ok(fornecedorService.atualizar(id, dto));
	}

	@GetMapping
	public ResponseEntity<Page<FornecedorResponseDTO>> listar(Pageable pageable) {
		return ResponseEntity.ok(fornecedorService.listar(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<FornecedorResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(fornecedorService.buscarPorId(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		fornecedorService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}