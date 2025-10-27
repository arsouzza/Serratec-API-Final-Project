package br.com.ecommerce.service;

import br.com.ecommerce.dto.CategoriaDTO;
import br.com.ecommerce.entity.Categoria;
import br.com.ecommerce.exception.RecursoNaoEncontradoException;
import br.com.ecommerce.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Categoria entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com id: " + id));
        return new CategoriaDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        List<Categoria> list = repository.findAll();
        return list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());
        entity = repository.save(entity);
        return new CategoriaDTO(entity);
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria entity = repository.getReferenceById(id);
            entity.setNome(dto.getNome());
            entity = repository.save(entity);
            return new CategoriaDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException("Categoria não encontrada com id: " + id);
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Categoria não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}