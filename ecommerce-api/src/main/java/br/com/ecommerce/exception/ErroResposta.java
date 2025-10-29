package br.com.ecommerce.exception;

import java.time.LocalDate;

import java.util.List;

public class ErroResposta {
	
	private Integer status;
	private String titulo;
	private LocalDate datahora;
	private List<String> erros;

	public ErroResposta(Integer status, String titulo, LocalDate datahora, List<String> erros) {
		super();
		this.status = status;
		this.titulo = titulo;
		this.datahora = datahora;
		this.erros = erros;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getDatahora() {
		return datahora;
	}

	public void setDatahora(LocalDate datahora) {
		this.datahora = datahora;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}
