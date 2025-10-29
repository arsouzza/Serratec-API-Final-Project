package br.com.ecommerce.dto;

import br.com.ecommerce.entity.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    String nomeFantasia,
    String razaoSocial,
    String cnpj,
    String email
) {
    public static FornecedorResponseDTO fromEntity(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNomeFantasia(),
            fornecedor.getRazaoSocial(),
            fornecedor.getCnpj(),
            fornecedor.getEmail()
        );
    }
}