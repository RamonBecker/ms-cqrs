package br.com.beautique.controllers;


import br.com.beautique.dtos.BeautyProcedureDTO;
import br.com.beautique.services.IBeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("beauty-procedures")
public class BeautyProcedureController {

    @Autowired
    private IBeautyProcedureService beautyProcedureService;

    @PostMapping
    ResponseEntity<BeautyProcedureDTO> create(@RequestBody BeautyProcedureDTO beautyProcedure) {
        return ResponseEntity.ok(beautyProcedureService.create(beautyProcedure));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        beautyProcedureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
