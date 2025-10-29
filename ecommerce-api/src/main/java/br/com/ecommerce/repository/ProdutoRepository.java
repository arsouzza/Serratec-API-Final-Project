package br.com.ecommerce.repository;

import br.com.ecommerce.entity.Produto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Page<Produto> findByCategoriaId(Long categoria, Pageable pageable);
}