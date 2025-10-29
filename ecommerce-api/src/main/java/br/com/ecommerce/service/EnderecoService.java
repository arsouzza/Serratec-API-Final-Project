package br.com.ecommerce.service;

import br.com.ecommerce.entity.Cliente;
import br.com.ecommerce.entity.Endereco;
import br.com.ecommerce.exception.ResourceNotFoundException;
import br.com.ecommerce.repository.EnderecoRepository;
import br.com.ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepo;
    private final ClienteRepository clienteRepo;

    public EnderecoService(EnderecoRepository enderecoRepo, ClienteRepository clienteRepo) {
        this.enderecoRepo = enderecoRepo;
        this.clienteRepo = clienteRepo;
    }

    public List<Endereco> listar() {
        return enderecoRepo.findAll();
    }

    public List<Endereco> listarPorCliente(Long clienteId) {
        // retorna lista vazia se cliente não existir? optamos por validar cliente primeiro
        clienteRepo.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + clienteId));
        return enderecoRepo.findByClienteId(clienteId);
    }

    public Endereco buscar(Long id) {
        return enderecoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado: " + id));
    }

    @Transactional
    public Endereco criar(Long clienteId, Endereco endereco) {
        Cliente cliente = clienteRepo.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + clienteId));
        endereco.setCliente(cliente);
        return enderecoRepo.save(endereco);
    }

    @Transactional
    public Endereco criar(Endereco endereco) {
        // se já vier com cliente associado, valida
        if (endereco.getCliente() != null) {
            Long clienteId = endereco.getCliente().getId();
            clienteRepo.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + clienteId));
        }
        return enderecoRepo.save(endereco);
    }

    @Transactional
    public Endereco atualizar(Long id, Endereco novoEndereco) {
        Endereco existente = enderecoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado: " + id));

        if (novoEndereco.getCep() != null) existente.setCep(novoEndereco.getCep());
        if (novoEndereco.getLogradouro() != null) existente.setLogradouro(novoEndereco.getLogradouro());
        if (novoEndereco.getComplemento() != null) existente.setComplemento(novoEndereco.getComplemento());
        if (novoEndereco.getBairro() != null) existente.setBairro(novoEndereco.getBairro());
        if (novoEndereco.getLocalidade() != null) existente.setLocalidade(novoEndereco.getLocalidade());
        if (novoEndereco.getUf() != null) existente.setUf(novoEndereco.getUf());

        // atualizar cliente associado (opcional)
        if (novoEndereco.getCliente() != null && novoEndereco.getCliente().getId() != null) {
            Long clienteId = novoEndereco.getCliente().getId();
            Cliente cliente = clienteRepo.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado: " + clienteId));
            existente.setCliente(cliente);
        }

        return enderecoRepo.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!enderecoRepo.existsById(id)) {
            throw new ResourceNotFoundException("Endereço não encontrado: " + id);
        }
        enderecoRepo.deleteById(id);
    }
}
