package br.com.ecommerce.dto;

import java.io.Serializable;

import br.com.ecommerce.entity.Produto;

public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String imgUrl;
    private CategoriaDTO categoria;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String nome, String descricao, Double preco, String imgUrl, CategoriaDTO categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl; 
        this.categoria = categoria;
    }

    public ProdutoDTO(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.preco = entity.getPreco();
        this.imgUrl = entity.getImgUrl(); 
        this.categoria = new CategoriaDTO(entity.getCategoria());
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


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}