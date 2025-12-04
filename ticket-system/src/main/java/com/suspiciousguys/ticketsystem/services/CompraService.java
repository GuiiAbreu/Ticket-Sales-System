package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.dtos.CompraDTO;
import com.suspiciousguys.ticketsystem.models.ClienteModel;
import com.suspiciousguys.ticketsystem.models.CompraModel;
import com.suspiciousguys.ticketsystem.repositories.ClienteRepository;
import com.suspiciousguys.ticketsystem.repositories.CompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public CompraService(CompraRepository compraRepository, ClienteService clienteService, ClienteRepository clienteRepository) {
        this.compraRepository = compraRepository;
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    public CompraDTO createCompra(CompraDTO compraDTO) {
        CompraModel newCompra = new CompraModel(compraDTO);
        ClienteModel cliente = new ClienteModel(clienteService.findById(compraDTO.getClienteId()));
        newCompra.setCliente(cliente);
        cliente.getCompras().add(newCompra);
        compraRepository.save(newCompra);
        clienteRepository.save(cliente);
        return new CompraDTO(newCompra);
    }

/*    public CompraDTO updateCompra(CompraDTO compraDTO, Long id) {
        CompraModel newCompra = new CompraModel(compraDTO);
        CompraModel oldCompra = compraRepository.findById(id).get();

    }*/

    public void deleteCompra(Long id) {
        compraRepository.deleteById(id);
    }

    public List<CompraDTO> getAllCompras() {
        List<CompraModel> compras = compraRepository.findAll();
        return compras.stream().map(CompraDTO::new).toList();
    }

    public CompraDTO getCompraById(Long id) {
        CompraModel compra = compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada."));
        return new CompraDTO(compra);
    }

}


