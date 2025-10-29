package br.com.ecommerce.dto;

<<<<<<< HEAD


   public record CategoriaResponseDTO(
		    
		) {
		   
		}
	


=======
import br.com.ecommerce.entity.Categoria;

   public record CategoriaResponseDTO(
		    Long id,
		    String nome,
		    String descricao
		) {
		    public static CategoriaResponseDTO fromEntity(Categoria categoria) {
		        return new CategoriaResponseDTO(
		            categoria.getId(),
		            categoria.getNome(),
		            categoria.getDescricao()
		        );
		    }
		}
	
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
