package br.com.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.ecommerce.entity.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select coalesce(sum(p.valor), 0) from Pedido p where p.cliente.id = :clienteId")
    Double somaTotalPorCliente(@Param("clienteId") Long clienteId);
}