package br.com.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FornecedorRequestDTO {

    @NotBlank(message = "O nome fantasia é obrigatório!")
    private String nomeFantasia;

    @NotBlank(message = "A razão social é obrigatória!")
    private String razaoSocial;

    @NotBlank(message = "O CNPJ é obrigatório!")
    @Size(min = 14, max = 14, message = "O CNPJ deve ter 14 dígitos.")
    private String cnpj;

    @NotBlank(message = "O e-mail é obrigatório!")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    public FornecedorRequestDTO() {
    }

    public FornecedorRequestDTO(String nomeFantasia, String razaoSocial, String cnpj, String email) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.email = email;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}