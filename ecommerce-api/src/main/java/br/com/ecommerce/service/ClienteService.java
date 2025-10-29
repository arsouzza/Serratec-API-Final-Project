package br.com.ecommerce.service;

import br.com.ecommerce.dto.ClienteDTO;
import br.com.ecommerce.entity.Cliente;
import br.com.ecommerce.entity.Endereco;
import br.com.ecommerce.exception.ResourceNotFoundException;
import br.com.ecommerce.repository.ClienteRepository;
import br.com.ecommerce.repository.EnderecoRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepo;
	private final EnderecoRepository enderecoRepo;
	private final JavaMailSender mailSender;
	private final RestTemplate restTemplate;

	public ClienteService(ClienteRepository clienteRepo, EnderecoRepository enderecoRepo, JavaMailSender mailSender,
			RestTemplate restTemplate) {
		this.clienteRepo = clienteRepo;
		this.enderecoRepo = enderecoRepo;
		this.mailSender = mailSender;
		this.restTemplate = restTemplate;
	}

	public List<Cliente> listar() {
		return clienteRepo.findAll();
	}

	public Cliente criar(ClienteDTO dto) {
		Cliente c = new Cliente();
		c.setNome(dto.getNome());
		c.setEmail(dto.getEmail());
		c.setTelefone(dto.getTelefone());
		c.setCpf(dto.getCpf());
		Cliente salvo = clienteRepo.save(c);
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(salvo.getEmail());
			msg.setSubject("Cadastro - ECommerce");
			msg.setText("Olá " + salvo.getNome() + ", seu cadastro foi criado.");
			mailSender.send(msg);
		} catch (Exception e) {
			System.out.println("Falha ao enviar email: " + e.getMessage());
		}
		return salvo;
	}

	public Cliente buscar(Long id) {
		return clienteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
	}

	public Cliente atualizar(Long id, ClienteDTO dto) {
		Cliente c = buscar(id);
		c.setNome(dto.getNome());
		c.setEmail(dto.getEmail());
		c.setTelefone(dto.getTelefone());
		c.setCpf(dto.getCpf());
		Cliente salvo = clienteRepo.save(c);
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(salvo.getEmail());
			msg.setSubject("Atualização de cadastro - ECommerce");
			msg.setText("Olá " + salvo.getNome() + ", seus dados foram atualizados.");
			mailSender.send(msg);
		} catch (Exception e) {
			System.out.println("Falha ao enviar email: " + e.getMessage());
		}
		return salvo;
	}
	
    public void deletar(Long id) {
        if (!clienteRepo.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado: " + id);
        }
        clienteRepo.deleteById(id);
    }

	public Endereco criarEnderecoPorCep(Long clienteId, String cep) {
		Cliente cliente = buscar(clienteId);
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		@SuppressWarnings("unchecked")
		java.util.Map<String, Object> resp = restTemplate.getForObject(url, java.util.Map.class);
		if (resp == null || resp.get("erro") != null)
			throw new ResourceNotFoundException("CEP não encontrado");
		Endereco e = new Endereco();
		e.setCep((String) resp.get("cep"));
		e.setLogradouro((String) resp.get("logradouro"));
		e.setComplemento((String) resp.get("complemento"));
		e.setBairro((String) resp.get("bairro"));
		e.setLocalidade((String) resp.get("localidade"));
		e.setUf((String) resp.get("uf"));
		e.setCliente(cliente);
		return enderecoRepo.save(e);
	}
}
