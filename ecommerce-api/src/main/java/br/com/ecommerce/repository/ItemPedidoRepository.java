package br.com.ecommerce.repository;

import br.com.ecommerce.entity.ItemPedido;
import br.com.ecommerce.entity.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}