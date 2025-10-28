package br.com.ecommerce.controller;

import br.com.ecommerce.dto.PedidoDTO;
import br.com.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
        PedidoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}/status")
    public ResponseEntity<PedidoDTO> updateStatus(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        dto = service.updateStatus(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}