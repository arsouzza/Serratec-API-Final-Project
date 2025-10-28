package br.com.ecommerce.security.controller;

import br.com.ecommerce.entity.Cliente;
import br.com.ecommerce.security.dto.AuthenticationRequestDTO;
import br.com.ecommerce.security.dto.AuthenticationResponseDTO;
import br.com.ecommerce.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO authDto) {
        
        UsernamePasswordAuthenticationToken usernamePassword = 
                new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getSenha());
        
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        
        Cliente cliente = (Cliente) auth.getPrincipal();
        
        String token = tokenService.generateToken(cliente);
        
        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }
}