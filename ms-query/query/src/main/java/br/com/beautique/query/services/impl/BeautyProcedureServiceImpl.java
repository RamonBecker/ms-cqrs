package br.com.beautique.query.services.impl;

import br.com.beautique.query.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.query.repositories.IBeautyProcedureRepository;
import br.com.beautique.query.services.IBeautyProcedureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeautyProcedureServiceImpl implements IBeautyProcedureService {

    private final IBeautyProcedureRepository beautyProcedureRepository;

    public BeautyProcedureServiceImpl(IBeautyProcedureRepository beautyProcedureRepository) {
        this.beautyProcedureRepository = beautyProcedureRepository;
    }

    @Override
    public List<BeautyProcedureDTO> listAllBeautyProcedures() {
        try {
            return beautyProcedureRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error listing all beauty procedures!");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameIgnoreCase(String name) {
        try {
            return beautyProcedureRepository.findByNameLikeIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all beauty procedures by name!");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description) {
        try {
            return beautyProcedureRepository.findByDescriptionLikeIgnoreCase(description);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all beauty procedures by description!");
        }
    }
}
