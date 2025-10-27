package br.com.ecommerce.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.dto.CategoriaRequestDTO;
import br.com.ecommerce.dto.CategoriaResponseDTO;
import br.com.ecommerce.entity.Categoria;
import br.com.ecommerce.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
	public CategoriaResponseDTO inserir(@RequestBody CategoriaRequestDTO dto) {
        if (categoriaRepository.existsByNome(dto.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com esse nome.");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        categoriaRepository.save(categoria);
        return CategoriaResponseDTO.fromEntity(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        categoriaRepository.save(categoria);
        return CategoriaResponseDTO.fromEntity(categoria);
    }

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/buscar{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable  Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
        return CategoriaResponseDTO.fromEntity(categoria);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada para exclusão.");
        }
        categoriaRepository.deleteById(id);
    }

}
