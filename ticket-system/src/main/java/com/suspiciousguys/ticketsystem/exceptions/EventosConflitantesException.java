package com.suspiciousguys.ticketsystem.exceptions;

import com.suspiciousguys.ticketsystem.dtos.EventoDTO;

import java.util.List;

public class EventosConflitantesException extends RuntimeException {
  private List<EventoDTO> eventosConflitantes;

  public EventosConflitantesException(List<EventoDTO> eventosConflitantes) {
    super("Existem eventos conflitantes.");
    this.eventosConflitantes = eventosConflitantes;
  }

  public List<EventoDTO> getEventosConflitantes() {
    return eventosConflitantes;
  }
}
