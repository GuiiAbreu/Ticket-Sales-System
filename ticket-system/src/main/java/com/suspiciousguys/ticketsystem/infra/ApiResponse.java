package com.suspiciousguys.ticketsystem.infra;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Classe genérica para resposta
@Data
public class ApiResponse<T> {

    private String mensagem;
    private T dados;
    private HttpStatus status;

    public ApiResponse(String mensagem, T dados, HttpStatus status) {
        this.mensagem = mensagem;
        this.dados = dados;
        this.status = status;
    }

    public ApiResponse(String mensagem, HttpStatus status) {
        this.mensagem = mensagem;
        this.status = status;
    }



    public static <T> ResponseEntity<ApiResponse<T>> createResponse(String mensagem, T dados, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(mensagem, dados, status);
        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> createResponse(String mensagem, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(mensagem, status);
        return new ResponseEntity<>(response, status);
    }
}
