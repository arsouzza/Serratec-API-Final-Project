package br.com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.dto.ProdutoDTO;
import br.com.ecommerce.entity.Categoria;
import br.com.ecommerce.entity.Produto;
import br.com.ecommerce.exception.RecursoNaoEncontradoException;
import br.com.ecommerce.repository.CategoriaRepository;
import br.com.ecommerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        Produto entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com id: " + id));
        return new ProdutoDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> findAll(Pageable pageable) {
        Page<Produto> page = repository.findAll(pageable);
        return page.map(ProdutoDTO::new);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        copiarDtoParaEntidade(dto, entity);
        entity = repository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        try {
            Produto entity = repository.getReferenceById(id);
            copiarDtoParaEntidade(dto, entity);
            entity = repository.save(entity);
            return new ProdutoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException("Produto não encontrado com id: " + id);
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
    
    private void copiarDtoParaEntidade(ProdutoDTO dto, Produto entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImgUrl(dto.getImgUrl());

        if (dto.getCategoria() != null && dto.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com id: " + dto.getCategoria().getId()));
            entity.setCategoria(categoria);
        } else {
            throw new RecursoNaoEncontradoException("Categoria é obrigatória.");
        }
    }
}