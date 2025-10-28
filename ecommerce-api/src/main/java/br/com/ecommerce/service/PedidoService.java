package br.com.ecommerce.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.ecommerce.dto.ItemPedidoDTO;
import br.com.ecommerce.dto.PedidoDTO;
import br.com.ecommerce.entity.Cliente;
import br.com.ecommerce.entity.ItemPedido;
import br.com.ecommerce.entity.Pedido;
import br.com.ecommerce.entity.Produto;
import br.com.ecommerce.enums.StatusPedido;
import br.com.ecommerce.exception.RecursoNaoEncontradoException;
import br.com.ecommerce.repository.ClienteRepository;
import br.com.ecommerce.repository.PedidoRepository;
import br.com.ecommerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        Pedido entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com id: " + id));
        return new PedidoDTO(entity);
    }

    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        Pedido entity = new Pedido();
        
        entity.setInstante(Instant.now());
        entity.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        Long clienteId = dto.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com id: " + clienteId));
        
        entity.setCliente(cliente);

        for (ItemPedidoDTO itemDto : dto.getItens()) {
            Long produtoId = itemDto.getProduto().getId();
            Produto produto = produtoRepository.findById(produtoId)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com id: " + produtoId));

            ItemPedido itemEntity = new ItemPedido();
            itemEntity.setPedido(entity);
            itemEntity.setProduto(produto);
            itemEntity.setQuantidade(itemDto.getQuantidade());
            itemEntity.setPrecoVenda(produto.getPreco());
            
            if (itemDto.getDesconto() != null) {
                itemEntity.setDesconto(itemDto.getDesconto());
            } else {
                itemEntity.setDesconto(0.0);
            }
            
            entity.getItens().add(itemEntity);
        }

        entity = repository.save(entity);
        return new PedidoDTO(entity);
    }

    @Transactional
    public PedidoDTO updateStatus(Long id, PedidoDTO dto) {
        try {
            Pedido entity = repository.getReferenceById(id);
            
            if (dto.getStatus() == null) {
                 throw new RecursoNaoEncontradoException("Status do pedido não pode ser nulo.");
            }
            
            entity.setStatus(dto.getStatus());
            entity = repository.save(entity);
            return new PedidoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontradoException("Pedido não encontrado com id: " + id);
        }
    }
}