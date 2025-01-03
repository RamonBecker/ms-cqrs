package br.com.beautique.ms_sync.services.impl;

import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.repositories.IBeautyProcedureRepository;
import br.com.beautique.ms_sync.services.IBeautyProcedureService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BeautyProcedureServiceImpl implements IBeautyProcedureService {

    @Autowired
    private IBeautyProcedureRepository beautyProcedureRepository;

    @Override
    public void save(BeautyProcedureDTO beautyProcedure) {
        try {

            SyncLogger.info("Saving beauty procedure: " + beautyProcedure.getId());
            beautyProcedureRepository.save(beautyProcedure);
        } catch (Exception e) {
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
