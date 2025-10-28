package br.com.ecommerce.security.dto;

import java.io.Serializable;

public class AuthenticationRequestDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String senha;

    public AuthenticationRequestDTO() {
    }

    public AuthenticationRequestDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}