package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.RespostaCampoDTO;
import com.suspiciousguys.ticketsystem.infra.ApiResponse;
import com.suspiciousguys.ticketsystem.services.RespostaCampoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resposta-campo")
public class RespostaCampoController {

    private final RespostaCampoService respostaCampoService;

    RespostaCampoController(RespostaCampoService respostaCampoService) {
        this.respostaCampoService = respostaCampoService;
    }

/*
    // Cria uma nova resposta de campo
    @PostMapping
    ResponseEntity<ApiResponse<RespostaCampoDTO>> createResposta(@RequestBody @Valid RespostaCampoDTO respostaCampoDTO) {
        RespostaCampoDTO respostaCriada = respostaCampoService.create(respostaCampoDTO);
        return ApiResponse.createResponse("Resposta criada com sucesso.", respostaCriada, HttpStatus.CREATED);
    }

    // Atualiza uma resposta de campo existente
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<RespostaCampoDTO>> updateResposta(
            @PathVariable Long id,
            @RequestBody @Valid RespostaCampoDTO respostaCampoDTO
    ) {
        RespostaCampoDTO respostaAtualizada = respostaCampoService.update(respostaCampoDTO, id);
        return ApiResponse.createResponse("Resposta atualizada com sucesso.", respostaAtualizada, HttpStatus.OK);
    }

    // Exclui uma resposta de campo pelo ID
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteResposta(@PathVariable Long id) {
        respostaCampoService.delete(id);
        return ApiResponse.createResponse("Resposta excluída com sucesso.", HttpStatus.OK);
    }
*/

    // Busca uma resposta de campo pelo ID
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<RespostaCampoDTO>> findRespostaById(@PathVariable Long id) {
        RespostaCampoDTO resposta = respostaCampoService.findById(id);
        return ApiResponse.createResponse("Resposta encontrada.", resposta, HttpStatus.OK);
    }

    // Lista todas as respostas de campo
    @GetMapping
    ResponseEntity<ApiResponse<List<RespostaCampoDTO>>> findAllRespostas() {
        List<RespostaCampoDTO> respostas = respostaCampoService.findAll();
        return ApiResponse.createResponse("Lista de respostas.", respostas, HttpStatus.OK);
    }
}