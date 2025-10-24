package br.com.ecommerce.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public Page<Produto> findByCategoriaId(Long categoriaId, Pageable pageable);

}
