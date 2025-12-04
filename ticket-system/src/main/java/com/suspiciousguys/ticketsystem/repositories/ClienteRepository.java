package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    @Query("SELECT c FROM ClienteModel c JOIN FETCH c.compras")
    List<ClienteModel> findAllWithCompras();

}
