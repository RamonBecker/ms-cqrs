package br.com.beautique.query.controllers;

import br.com.beautique.query.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.query.services.IBeautyProcedureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beauty-procedure")
public class BeautyProcedureController {

    private final IBeautyProcedureService beautyProcedureService;

    public BeautyProcedureController(IBeautyProcedureService beautyProcedureService) {
        this.beautyProcedureService = beautyProcedureService;
    }

    @GetMapping
    ResponseEntity<List<BeautyProcedureDTO>> listAllBeautyProcedures() {
        return ResponseEntity.ok(beautyProcedureService.listAllBeautyProcedures());
    }

    @GetMapping("/name/{name}")
    ResponseEntity<List<BeautyProcedureDTO>> listByNameIgnoreCase(@PathVariable String name) {
        return ResponseEntity.ok(beautyProcedureService.listByNameIgnoreCase(name));
    }

    @GetMapping("/description/{description}")
    ResponseEntity<List<BeautyProcedureDTO>> listByDescriptionIgnoreCase(@PathVariable String description) {
        return ResponseEntity.ok(beautyProcedureService.listByDescriptionIgnoreCase(description));
    }
}
