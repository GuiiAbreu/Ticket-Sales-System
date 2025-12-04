package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.CampoDTO;
import com.suspiciousguys.ticketsystem.infra.ApiResponse;
import com.suspiciousguys.ticketsystem.services.CampoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campo")
public class CampoController {

    private final CampoService campoService;

    public CampoController(CampoService campoService) {
        this.campoService = campoService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<CampoDTO>> create(@RequestBody @Valid CampoDTO campoDTO, @PathVariable Long id) {
        CampoDTO newCampo = campoService.createCampo(campoDTO,id);
        return ApiResponse.createResponse("Campo criado com sucesso.", newCampo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CampoDTO>> update(@PathVariable Long id, @RequestBody @Valid CampoDTO campoDTO) {
        CampoDTO updatedCampo = campoService.updateCampo(campoDTO, id);
        return ApiResponse.createResponse("Campo atualizado com sucesso.", updatedCampo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        campoService.deleteCampo(id);
        return ApiResponse.createResponse("Campo deletado com sucesso.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CampoDTO>>> findAll() {
        List<CampoDTO> campos = campoService.getAllCampos();
        return ApiResponse.createResponse("Todos os campos.", campos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CampoDTO>> findById(@PathVariable Long id) {
        CampoDTO campo = campoService.getCampoById(id);
        return ApiResponse.createResponse("Campo encontrado.", campo, HttpStatus.OK);
    }
}
