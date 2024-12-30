package br.com.beautique.services.impl;

import br.com.beautique.dtos.BeautyProcedureDTO;
import br.com.beautique.entities.BeautyProceduresEntity;
import br.com.beautique.repositories.IBeautyProcedureRepository;
import br.com.beautique.services.IBeautyProcedureService;
import br.com.beautique.services.IBrokerService;
import br.com.beautique.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeautyProcedureServiceImpl implements IBeautyProcedureService {

    @Autowired
    private IBeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private IBrokerService brokerService;

    private final ConvertUtil<BeautyProceduresEntity, BeautyProcedureDTO> convertUtil = new ConvertUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);



    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedure) {

        var beautyProcedureEntity = convertUtil.convertToSource(beautyProcedure);
        var newBeautyProcedureEntity = beautyProcedureRepository.save(beautyProcedureEntity);

        sendBeautyProceduresQueue(newBeautyProcedureEntity);

        return convertUtil.convertToTarget(newBeautyProcedureEntity);
    }

    @Override
    public void deleteById(Long id) {
        var findBeautyProcedure = beautyProcedureRepository.findById(id);

        if (findBeautyProcedure.isEmpty())
            throw new RuntimeException("Beauty Procedure not found!");

        beautyProcedureRepository.deleteById(id);
    }

    @Override
    public BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedure) {

        var findBeautyProcedure = beautyProcedureRepository.findById(beautyProcedure.getId());

        if (findBeautyProcedure.isEmpty())
            throw new RuntimeException("Beauty Procedure not found!");

        var beautyProcedureEntity = convertUtil.convertToSource(beautyProcedure);

        beautyProcedureEntity.setAppointments(findBeautyProcedure.get().getAppointments());
        beautyProcedureEntity.setCreatedAt(findBeautyProcedure.get().getCreatedAt());

        var updateBeautyProcedure = beautyProcedureRepository.save(beautyProcedureEntity);

        sendBeautyProceduresQueue(updateBeautyProcedure);

        return convertUtil.convertToTarget(updateBeautyProcedure);
    }

    private void sendBeautyProceduresQueue(BeautyProceduresEntity beautyProcedures){
        var beautyProcedureDTO = BeautyProcedureDTO.builder()
                .id(beautyProcedures.getId())
                .name(beautyProcedures.getName())
                .description(beautyProcedures.getDescription())
                .price(beautyProcedures.getPrice())
                .build();

        brokerService.send("beautyProcedures",beautyProcedureDTO);
    }
}
