package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.RespostaCampoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaCampoRepository extends JpaRepository<RespostaCampoModel, Long> {

}
