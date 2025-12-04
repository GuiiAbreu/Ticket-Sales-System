package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.IngressoCompradoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoCompradoRepository extends JpaRepository<IngressoCompradoModel, Long> {
}
