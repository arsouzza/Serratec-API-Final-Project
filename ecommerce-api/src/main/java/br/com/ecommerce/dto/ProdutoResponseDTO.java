package br.com.ecommerce.dto;

import br.com.ecommerce.entity.Produto;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    String categoriaNome,
    Integer quantidadeEstoque
) {
    public static ProdutoResponseDTO fromEntity(Produto produto) {
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getPreco(),
            produto.getCategoria().getNome(),
            produto.getQuantidadeEstoque()
        );
    }
}
