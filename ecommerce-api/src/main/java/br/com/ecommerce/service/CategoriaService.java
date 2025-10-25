package br.com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.dto.CategoriaRequestDTO;
import br.com.ecommerce.dto.CategoriaResponseDTO;
import br.com.ecommerce.entity.Categoria;
import br.com.ecommerce.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	 public CategoriaResponseDTO inserir(CategoriaRequestDTO dto) {
	        if (categoriaRepository.existsByNome(dto.getNome())) {
	            throw new IllegalArgumentException("Já existe uma categoria com esse nome.");
	        }

	        Categoria categoria = new Categoria();
	        categoria.setNome(dto.getNome());
	        categoria.setDescricao(dto.getDescricao());

	        categoriaRepository.save(categoria);
	        return CategoriaResponseDTO.fromEntity(categoria);
	    }

	    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
	        Categoria categoria = categoriaRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));

	        categoria.setNome(dto.getNome());
	        categoria.setDescricao(dto.getDescricao());

	        categoriaRepository.save(categoria);
	        return CategoriaResponseDTO.fromEntity(categoria);
	    }

	    public List<CategoriaResponseDTO> listar() {
	        return categoriaRepository.findAll()
	                .stream()
	                .map(CategoriaResponseDTO::fromEntity)
	                .toList();
	    }

	    public CategoriaResponseDTO buscarPorId(Long id) {
	        Categoria categoria = categoriaRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
	        return CategoriaResponseDTO.fromEntity(categoria);
	    }

	    public void deletar(Long id) {
	        if (!categoriaRepository.existsById(id)) {
	            throw new EntityNotFoundException("Categoria não encontrada para exclusão.");
	        }
	        categoriaRepository.deleteById(id);
	    }
	}


