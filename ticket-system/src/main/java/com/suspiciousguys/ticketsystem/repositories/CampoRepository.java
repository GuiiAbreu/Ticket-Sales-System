package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.CampoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampoRepository extends JpaRepository<CampoModel, Long> {
}
