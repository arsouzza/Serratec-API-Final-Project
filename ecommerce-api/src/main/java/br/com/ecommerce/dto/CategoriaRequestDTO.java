package br.com.ecommerce.dto;

<<<<<<< HEAD
public class CategoriaRequestDTO {

}
=======
import jakarta.validation.constraints.NotBlank;

public class CategoriaRequestDTO {
	
	@NotBlank(message = "O nome é obrigatório!")
	String nome;
	
	@NotBlank(message = "A descrição é obrigatória!")
	String descricao;
	
	public CategoriaRequestDTO() {
		
	}

	public CategoriaRequestDTO(@NotBlank(message = "O nome é obrigatório!") String nome,
			@NotBlank(message = "A descrição é obrigatória!") String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
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

	
	

}
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
