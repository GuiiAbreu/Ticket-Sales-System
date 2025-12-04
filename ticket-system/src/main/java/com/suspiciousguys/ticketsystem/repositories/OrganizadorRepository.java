package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.OrganizadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends JpaRepository<OrganizadorModel, Long> {
}
