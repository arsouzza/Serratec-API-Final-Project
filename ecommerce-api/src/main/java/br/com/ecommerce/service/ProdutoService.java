package br.com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.ecommerce.dto.ProdutoRequestDTO;
import br.com.ecommerce.dto.ProdutoResponseDTO;
import br.com.ecommerce.entity.Categoria;
import br.com.ecommerce.entity.Produto;
import br.com.ecommerce.repository.CategoriaRepository;
import br.com.ecommerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Page<ProdutoResponseDTO> listar(Pageable pageable) {
		return produtoRepository.findAll(pageable).map(ProdutoResponseDTO::fromEntity);

	}

	public Page<ProdutoResponseDTO> listarPorCategoria(Long categoriaId, Pageable pageable) {
		return produtoRepository.findByCategoriaId(categoriaId, pageable).map(ProdutoResponseDTO::fromEntity);
	}

	public ProdutoResponseDTO buscarPorId(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
		return ProdutoResponseDTO.fromEntity(produto);
	}

	public ProdutoResponseDTO inserir(ProdutoRequestDTO produtoRequestDTO) {
		Categoria categoria = categoriaRepository.findById(produtoRequestDTO.getCategoriaNome())
				.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));

		Produto produto = new Produto();
		produto.setNome(produtoRequestDTO.getNome());
		produto.setDescricao(produtoRequestDTO.getDescricao());
		produto.setPreco(produtoRequestDTO.getPreco());
		produto.setCategoria(categoria);

		produtoRepository.save(produto);
		return ProdutoResponseDTO.fromEntity(produto);
	}

	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

		Categoria categoria = categoriaRepository.findById(dto.getCategoriaNome())
				.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));

		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		produto.setCategoria(categoria);

		produtoRepository.save(produto);
		return ProdutoResponseDTO.fromEntity(produto);
	}

}
