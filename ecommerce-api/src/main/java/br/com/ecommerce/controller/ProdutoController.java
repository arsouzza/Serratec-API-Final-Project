package br.com.ecommerce.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.dto.ProdutoRequestDTO;
import br.com.ecommerce.dto.ProdutoResponseDTO;
import br.com.ecommerce.repository.ProdutoRepository;
import br.com.ecommerce.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService, ProdutoRepository produtoRepository) {
		this.produtoService = produtoService;
		this.produtoRepository = produtoRepository;
	}

	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> inserir(@Valid @RequestBody ProdutoRequestDTO dto) {
		ProdutoResponseDTO response = produtoService.inserir(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody ProdutoRequestDTO dto) {
		return ResponseEntity.ok(produtoService.atualizar(id, dto));
	}
	
	@PutMapping("/{id}/estoque")
	public ResponseEntity<ProdutoResponseDTO> atualizarEstoque(@PathVariable Long id, @RequestParam Integer quantidade){
		ProdutoResponseDTO response = produtoService.atualizarEstoque(id, quantidade);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoResponseDTO>> listar(Pageable pageable) {
		return ResponseEntity.ok(produtoService.listar(pageable));
	}

	@GetMapping("/categoria/{id}")
	public ResponseEntity<Page<ProdutoResponseDTO>> listarPorCategoria(@PathVariable Long id, Pageable pageable) {
		return ResponseEntity.ok(produtoService.listarPorCategoria(id, pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(produtoService.buscarPorId(id));
	}
	
	@DeleteMapping
	public void deletar(Long id) {
		if(produtoRepository.existsById(id)) {
			throw new EntityNotFoundException("Produto não encontrado para exclusão");
		}
		produtoRepository.deleteById(id);
	}
}
=======

public class ProdutoController {

    
}
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
