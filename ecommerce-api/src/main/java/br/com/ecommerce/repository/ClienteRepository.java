package br.com.ecommerce.repository;

<<<<<<< HEAD
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByCpf(String cpf);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.entity.Cliente;
>>>>>>> feature/merge-all

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
