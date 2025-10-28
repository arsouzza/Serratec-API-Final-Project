package br.com.ecommerce.security.dto;

import java.io.Serializable;

public class AuthenticationResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;

	public AuthenticationResponseDTO() {
	}

	public AuthenticationResponseDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}