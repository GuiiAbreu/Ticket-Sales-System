package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<EventoModel, Long> {

    @Query(value = "SELECT * FROM public.evento ORDER BY id ASC", nativeQuery = true)
    List<EventoModel> findAllOrderedNative();

    @Query("""
    SELECT e FROM EventoModel e
    JOIN e.datasEvento d
    WHERE e.local = :local
    AND d.data = :data
    AND (
        (e.horario <= :horarioFim AND e.horarioFim >= :horario)
    )
""")
    List<EventoModel> findEventosComConflito(
            @Param("local") String local,
            @Param("data") LocalDate data,
            @Param("horario") LocalTime horario,
            @Param("horarioFim") LocalTime horarioFim
    );



}

