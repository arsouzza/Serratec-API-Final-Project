package br.com.ecommerce.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 14)
    private String cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

=======
public class ClienteDTO {

}
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71
