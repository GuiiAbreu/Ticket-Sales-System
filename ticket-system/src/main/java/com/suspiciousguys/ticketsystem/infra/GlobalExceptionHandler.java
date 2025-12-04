package com.suspiciousguys.ticketsystem.infra;

import com.suspiciousguys.ticketsystem.dtos.EventoDTO;
import com.suspiciousguys.ticketsystem.exceptions.EventosConflitantesException;
import com.suspiciousguys.ticketsystem.exceptions.IngressosEsgotadosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(EventosConflitantesException.class)
    public ResponseEntity<ApiResponse<List<EventoDTO>>> eventosConflitantesException(EventosConflitantesException e) {
        List<EventoDTO> eventosConflitantes = e.getEventosConflitantes();
        String mensagemErro = "Existem eventos conflitantes no mesmo horário ou local.";

        // Cria a resposta com os eventos conflitantes
        return ApiResponse.createResponse( mensagemErro,eventosConflitantes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IngressosEsgotadosException.class)
    public ResponseEntity<String> handleIngressosEsgotadosException(IngressosEsgotadosException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
