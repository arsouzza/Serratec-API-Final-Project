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

	public ProdutoRequestDTO() {
		
	}

	public ProdutoRequestDTO(Long id, @NotBlank String nome, String descricao, Double preco, Integer estoque,
			Long categoriaId) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoriaId = categoriaId;
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

	public Long getCategoriaNome() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	
}
