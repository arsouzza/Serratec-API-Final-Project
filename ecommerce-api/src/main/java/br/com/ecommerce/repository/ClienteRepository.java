package br.com.ecommerce.repository;

<<<<<<< HEAD
import br.com.ecommerce.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByEmail(String email);

	Optional<Cliente> findByCpf(String cpf);

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);
=======
public class ClienteRepository {

>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
}
