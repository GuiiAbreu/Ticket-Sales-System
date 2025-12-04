package com.suspiciousguys.ticketsystem.controllers;

import com.suspiciousguys.ticketsystem.dtos.DatasEventoDTO;
import com.suspiciousguys.ticketsystem.models.DataEventoModel;
import com.suspiciousguys.ticketsystem.services.DataEventoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datasevento")
public class DatasEventoController {
    private final DataEventoService datasEventoService;

    public DatasEventoController(final DataEventoService datasEventoService) {
        this.datasEventoService = datasEventoService;
    }

    @PostMapping
    public ResponseEntity<DataEventoModel> create(@RequestBody @Valid DatasEventoDTO datasEventoDTO) {
        System.out.println(datasEventoDTO);
        DataEventoModel newDatasEvento = new DataEventoModel(datasEventoDTO);
        datasEventoService.create(newDatasEvento);
        return ResponseEntity.ok(newDatasEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataEventoModel> update(@PathVariable Long id, @RequestBody @Valid DatasEventoDTO datasEventoDTO) {
        DataEventoModel oldDatasEvento = datasEventoService.findById(id);
        DataEventoModel newDatasEvento = new DataEventoModel(datasEventoDTO);
        newDatasEvento.setId(oldDatasEvento.getId());
        datasEventoService.update(newDatasEvento);
        return ResponseEntity.ok(newDatasEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataEventoModel> delete(@PathVariable Long id) {
        DataEventoModel datasEvento = datasEventoService.findById(id);

        if (datasEvento == null) {
            return ResponseEntity.notFound().build();
        };
        datasEventoService.delete(datasEvento);
        return ResponseEntity.ok(datasEvento);
    }

    @GetMapping
    public ResponseEntity<List<DataEventoModel>> findAll() {
        List<DataEventoModel> allDatas = datasEventoService.findAll();
        return ResponseEntity.ok(allDatas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataEventoModel> findById(@PathVariable final Long id) {
        DataEventoModel datasEvento = datasEventoService.findById(id);
        return ResponseEntity.ok(datasEvento);
    }




}
