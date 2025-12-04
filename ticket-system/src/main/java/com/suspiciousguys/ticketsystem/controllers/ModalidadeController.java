package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.ModalidadeDTO;
import com.suspiciousguys.ticketsystem.infra.ApiResponse;
import com.suspiciousguys.ticketsystem.services.ModalidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modalidade")
public class ModalidadeController {

    private final ModalidadeService modalidadeService;

    public ModalidadeController(ModalidadeService modalidadeService) {
        this.modalidadeService = modalidadeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ModalidadeDTO>> save(@RequestBody @Valid ModalidadeDTO modalidadeDTO) {
        ModalidadeDTO newModalidade = modalidadeService.createModalidade(modalidadeDTO);
        return ApiResponse.createResponse("Modalidade criada com sucesso.", newModalidade, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ModalidadeDTO>> update(@PathVariable Long id, @RequestBody @Valid ModalidadeDTO modalidadeDTO) {
        ModalidadeDTO updatedModalidade = modalidadeService.updateModalidade(modalidadeDTO, id);
        return ApiResponse.createResponse("Modalidade atualizada com sucesso.", updatedModalidade, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        modalidadeService.deleteModalidade(id);
        return ApiResponse.createResponse("Modalidade deletada com sucesso.", HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<ModalidadeDTO>>> findAll() {
        List<ModalidadeDTO> modalidades = modalidadeService.getAllModalidades();
        return ApiResponse.createResponse("Todas as modalidades.", modalidades, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ModalidadeDTO>> findById(@PathVariable Long id) {
        ModalidadeDTO modalidade = modalidadeService.getModalidadeById(id);
        return ApiResponse.createResponse("Modalidade encontrada.", modalidade, HttpStatus.OK);
    }


}