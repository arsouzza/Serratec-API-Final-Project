package br.com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ecommerce.dto.FornecedorRequestDTO;
import br.com.ecommerce.dto.FornecedorResponseDTO;
import br.com.ecommerce.entity.Fornecedor;
import br.com.ecommerce.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Page<FornecedorResponseDTO> listar(Pageable pageable) {
        return fornecedorRepository.findAll(pageable)
                                   .map(FornecedorResponseDTO::fromEntity);
    }

    public FornecedorResponseDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado. ID: " + id));
        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO inserir(FornecedorRequestDTO dto) {
        if (fornecedorRepository.existsByCnpj(dto.getCnpj())) {
            throw new IllegalArgumentException("Já existe um fornecedor com este CNPJ.");
        }

        Fornecedor fornecedor = new Fornecedor();
        copiarDtoParaEntidade(dto, fornecedor);

        fornecedorRepository.save(fornecedor);
        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO atualizar(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado. ID: " + id));

        copiarDtoParaEntidade(dto, fornecedor);

        fornecedorRepository.save(fornecedor);
        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    @Transactional
    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new EntityNotFoundException("Fornecedor não encontrado para exclusão. ID: " + id);
        }
        fornecedorRepository.deleteById(id);
    }

    private void copiarDtoParaEntidade(FornecedorRequestDTO dto, Fornecedor entidade) {
        entidade.setNomeFantasia(dto.getNomeFantasia());
        entidade.setRazaoSocial(dto.getRazaoSocial());
        entidade.setCnpj(dto.getCnpj());
        entidade.setEmail(dto.getEmail());
    }
}