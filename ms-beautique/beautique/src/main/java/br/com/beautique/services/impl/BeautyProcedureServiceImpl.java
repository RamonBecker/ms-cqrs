package br.com.beautique.services.impl;

import br.com.beautique.dtos.BeautyProcedureDTO;
import br.com.beautique.entities.BeautyProceduresEntity;
import br.com.beautique.repositories.IBeautyProcedureRepository;
import br.com.beautique.services.IBeautyProcedureService;
import br.com.beautique.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeautyProcedureServiceImpl implements IBeautyProcedureService {

    @Autowired
    private IBeautyProcedureRepository beautyProcedureRepository;

    private final ConvertUtil<BeautyProceduresEntity, BeautyProcedureDTO> convertUtil = new ConvertUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedure) {

        var beautyProcedureEntity = convertUtil.convertToSource(beautyProcedure);
        var newBeautyProcedureEntity = beautyProcedureRepository.save(beautyProcedureEntity);

        return convertUtil.convertToTarget(newBeautyProcedureEntity);
    }

    @Override
    public void delete(Long id) {
        var findBeautyProcedure = beautyProcedureRepository.findById(id);

        if (findBeautyProcedure.isEmpty())
            throw new RuntimeException("Beauty Procedure not found!");

        beautyProcedureRepository.deleteById(id);
    }
}
