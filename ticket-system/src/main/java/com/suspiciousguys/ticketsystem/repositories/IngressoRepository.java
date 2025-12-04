package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.IngressoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<IngressoModel, Long> {
}
