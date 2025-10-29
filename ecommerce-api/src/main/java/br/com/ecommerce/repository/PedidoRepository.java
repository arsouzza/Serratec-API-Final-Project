package br.com.ecommerce.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.ecommerce.entity.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select coalesce(sum(p.valor), 0) from Pedido p where p.cliente.id = :clienteId")
    Double somaTotalPorCliente(@Param("clienteId") Long clienteId);
}
=======
public class PedidoRepository {

}
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
