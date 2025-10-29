package br.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ecommerce.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    boolean existsByCnpj(String cnpj);
}