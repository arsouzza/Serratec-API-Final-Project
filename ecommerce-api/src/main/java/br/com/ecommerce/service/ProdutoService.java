package br.com.ecommerce.service;

<<<<<<< HEAD
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
import jakarta.transaction.Transactional;

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

	@Transactional
	public ProdutoResponseDTO inserir(ProdutoRequestDTO produtoRequestDTO) {
		Categoria categoria = categoriaRepository.findById(produtoRequestDTO.getCategoriaId())
				.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));

		Produto produto = new Produto();
		produto.setNome(produtoRequestDTO.getNome());
		produto.setDescricao(produtoRequestDTO.getDescricao());
		produto.setPreco(produtoRequestDTO.getPreco());
		produto.setQuantidadeEstoque(produtoRequestDTO.getQuantidadeEstoque());
		produto.setCategoria(categoria);

		produtoRepository.save(produto);
		return ProdutoResponseDTO.fromEntity(produto);
	}

	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

		Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));

		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		produto.setCategoria(categoria);

		produtoRepository.save(produto);
		return ProdutoResponseDTO.fromEntity(produto);
	}
	
	@Transactional
	public ProdutoResponseDTO atualizarEstoque(Long id, Integer quantidade) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
	
		int novoEstoque = produto.getQuantidadeEstoque() + quantidade;
		if (novoEstoque <=0) {
			throw new IllegalArgumentException("Estoque não pode ser negativo");
		}
		produto.setQuantidadeEstoque(novoEstoque);
		produtoRepository.save(produto);
		
		return ProdutoResponseDTO.fromEntity(produto);
	}

}
=======

public class ProdutoService {

}
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
