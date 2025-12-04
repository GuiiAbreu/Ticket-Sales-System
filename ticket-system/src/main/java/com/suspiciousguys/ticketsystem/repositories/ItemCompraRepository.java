package com.suspiciousguys.ticketsystem.repositories;

import com.suspiciousguys.ticketsystem.models.ItemCompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompraModel, Long> {

    List<ItemCompraModel> findByCompraId(Long compraId);

    List<ItemCompraModel> findByModalidadeId(Long modalidadeId);
}
