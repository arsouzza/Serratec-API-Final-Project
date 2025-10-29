package br.com.ecommerce.dto;

<<<<<<< HEAD
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
=======
public record ProdutoResponseDTO() {
}
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
