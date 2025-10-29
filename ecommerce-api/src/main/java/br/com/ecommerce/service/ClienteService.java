package br.com.ecommerce.service;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.dto.ClienteDTO;
import br.com.ecommerce.entity.Cliente;
import br.com.ecommerce.exception.ResourceNotFoundException;
import br.com.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    private final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }


    public Cliente salvar(ClienteDTO dto) {
    	Cliente c = new Cliente();
    	    c.setNome(dto.getNome());
    	    c.setEmail(dto.getEmail());
    	    c.setTelefone(dto.getTelefone());
    	    c.setCpf(dto.getCpf());
    	    Cliente salvo = repository.save(c);
    	   
    	    return salvo;
    	}
    


    private void preencherEnderecoPorCep(Cliente cliente) {
        RestTemplate restTemplate = new RestTemplate();
        String url = VIA_CEP_URL.replace("{cep}", cliente.getCep());
        Cliente endereco = restTemplate.getForObject(url, Cliente.class);
        if (endereco != null) {
            cliente.setLogradouro(endereco.getLogradouro());
            cliente.setBairro(endereco.getBairro());
            cliente.setLocalidade(endereco.getLocalidade());
            cliente.setUf(endereco.getUf());
        }
    }


	public void deletar(Long id) {
		
		
	}
	public Cliente buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }
	public Cliente atualizar(Long id, ClienteDTO dto) {
        Cliente c = buscar(id);
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        c.setTelefone(dto.getTelefone());
        c.setCpf(dto.getCpf());
        Cliente salvo = repository.save(c);
        return salvo;
    }
=======
public class ClienteService {

>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
}
