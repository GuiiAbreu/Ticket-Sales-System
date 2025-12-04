package com.suspiciousguys.ticketsystem.services;

import com.suspiciousguys.ticketsystem.models.DataEventoModel;
import com.suspiciousguys.ticketsystem.repositories.DatasEventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataEventoService {

    private final DatasEventoRepository datasEventoRepository;

    DataEventoService(DatasEventoRepository datasEventoRepository) {
        this.datasEventoRepository = datasEventoRepository;
    }

    public void create(DataEventoModel dataEventoModel) {
        datasEventoRepository.save(dataEventoModel);
    }

    public void update(DataEventoModel dataEventoModel) {
        datasEventoRepository.save(dataEventoModel);
    }

    public void delete(DataEventoModel dataEventoModel) {
        datasEventoRepository.delete(dataEventoModel);
    }

    public List<DataEventoModel> findAll(){
        return datasEventoRepository.findAll();
    }

    public DataEventoModel findById(Long id) {
        return datasEventoRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum evento a exibir."));
    }


}
