package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.models.ItemCompraModel;
import com.suspiciousguys.ticketsystem.repositories.ItemCompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCompraService {


    private final ItemCompraRepository itemCompraRepository;

    public ItemCompraService(ItemCompraRepository itemCompraRepository) {
        this.itemCompraRepository = itemCompraRepository;
    }

    public ItemCompraModel getItemCompraById(Long id) {
        return itemCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de compra não encontrado"));
    }

    public List<ItemCompraModel> getItensCompraPorCompra(Long compraId) {
        return itemCompraRepository.findByCompraId(compraId);
    }

    public List<ItemCompraModel> getItensCompraPorModalidade(Long modalidadeId) {
        return itemCompraRepository.findByModalidadeId(modalidadeId);
    }

    @Transactional
    public void excluirItemCompra(Long id) {
        itemCompraRepository.deleteById(id);
    }
}