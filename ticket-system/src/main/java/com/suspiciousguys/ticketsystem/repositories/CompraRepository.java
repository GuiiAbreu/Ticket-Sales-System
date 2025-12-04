package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Long> {
}
