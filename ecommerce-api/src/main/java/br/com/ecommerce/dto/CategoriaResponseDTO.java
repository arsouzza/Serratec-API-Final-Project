package br.com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO genérico para padronização de respostas da API.
 * Renomeado de CategoriaResponseDTO para ApiResponseDTO para maior clareza.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO<T> {
    private String status;
    private String mensagem;
    private T dados;
}