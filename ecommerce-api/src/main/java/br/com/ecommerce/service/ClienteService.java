package br.com.ecommerce.service;

import br.com.ecommerce.dto.ClienteDTO;
import br.com.ecommerce.dto.EnderecoViaCepDTO;
import br.com.ecommerce.entity.Cliente;
import br.com.ecommerce.exception.RecursoNaoEncontradoException;
import br.com.ecommerce.repository.ClienteRepository;
import br.com.ecommerce.service.email.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		Cliente entity = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id));
		return new ClienteDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<ClienteDTO> findAll() {
		List<Cliente> list = repository.findAll();
		return list.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

	@Transactional
	public ClienteDTO insert(ClienteDTO dto) {
		Cliente entity = new Cliente();
		copiarDtoParaEntidade(dto, entity);

		entity.setSenha(passwordEncoder.encode(dto.getSenha()));

		entity = repository.save(entity);

		emailService.sendEmail(entity.getEmail(), "Cadastro realizado com sucesso!",
				"Olá, " + entity.getNome() + "! Seu cadastro em nosso E-Commerce foi realizado com sucesso.");

		return new ClienteDTO(entity);
	}

	@Transactional
	public ClienteDTO update(Long id, ClienteDTO dto) {
		try {
			Cliente entity = repository.getReferenceById(id);
			copiarDtoParaEntidade(dto, entity);

			if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
				entity.setSenha(passwordEncoder.encode(dto.getSenha()));
			}

			entity = repository.save(entity);

			emailService.sendEmail(entity.getEmail(), "Seus dados foram atualizados!",
					"Olá, " + entity.getNome() + "! Seus dados em nosso E-Commerce foram atualizados.");

			return new ClienteDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id);
		}
	}

	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id);
		}
		repository.deleteById(id);
	}

	private void copiarDtoParaEntidade(ClienteDTO dto, Cliente entity) {
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setCpf(dto.getCpf());
		entity.setTelefone(dto.getTelefone());
		entity.setCep(dto.getCep());

		if (dto.getCep() != null && !dto.getCep().isEmpty()) {
			EnderecoViaCepDTO enderecoDto = buscarEnderecoViaCep(dto.getCep());

			entity.setLogradouro(enderecoDto.getLogradouro());
			entity.setComplemento(enderecoDto.getComplemento());
			entity.setBairro(enderecoDto.getBairro());
			entity.setLocalidade(enderecoDto.getLocalidade());
			entity.setUf(enderecoDto.getUf());
		}
	}

	private EnderecoViaCepDTO buscarEnderecoViaCep(String cep) {
		String url = "https://viacê.com.br/ws/" + cep + "/json/"; 
		try {
			EnderecoViaCepDTO endereco = restTemplate.getForObject(url, EnderecoViaCepDTO.class);
			if (endereco == null || endereco.getCep() == null) {
				throw new RecursoNaoEncontradoException("CEP inválido ou não encontrado: " + cep);
			}
			return endereco;
		} catch (Exception e) {
			throw new RecursoNaoEncontradoException("Erro ao consultar o CEP: " + cep);
		}
	}
}