package br.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.entity.PedidoProduto;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {

}