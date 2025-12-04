package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.DataEventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DatasEventoRepository extends JpaRepository<DataEventoModel, Long> {
    DataEventoModel findByData(LocalDate data);


}
