package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.ModalidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadeRepository extends JpaRepository<ModalidadeModel, Long> {
}
