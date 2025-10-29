package br.com.ecommerce.controller;

import br.com.ecommerce.dto.PedidoRequestDTO;
import br.com.ecommerce.dto.PedidoResponseDTO;
import br.com.ecommerce.entity.Pedido;
import br.com.ecommerce.repository.PedidoRepository;
import br.com.ecommerce.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO resp = pedidoService.criar(dto);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
}