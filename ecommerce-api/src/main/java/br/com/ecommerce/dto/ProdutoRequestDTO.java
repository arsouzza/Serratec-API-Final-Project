package br.com.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

public class ProdutoRequestDTO {

	private Long id;

	@NotBlank(message = "O nome do produto é obrigatório!")
	private String nome;

	@NotBlank(message = "A descrição é obrigatória!")
	private String descricao;
	@NotNull(message = "O preço do produto é obrogatório!")
	private Double preco;
	@NotBlank(message = "O ID da categoria é obrigatório!")
	private Long categoriaId;
	private Integer quantidadeEstoque;

	public ProdutoRequestDTO() {

	}

	public ProdutoRequestDTO(Long id, String nome, String descricao, Double preco, Integer estoque,
			Integer quantidadeEstoque, Long categoriaId) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoriaId = categoriaId;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Long getCategoriaId() {
		return categoriaId;

	}

}
