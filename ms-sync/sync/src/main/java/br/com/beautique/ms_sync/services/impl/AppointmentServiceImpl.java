package br.com.beautique.ms_sync.services.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;

import br.com.beautique.ms_sync.repositories.IAppointmentRepository;
import br.com.beautique.ms_sync.services.IAppointmentService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(FullAppointmentDTO appointment) {
        try{
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentRepository.save(appointment);
        }catch (Exception e){
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Updating appointment customer: " + customer.getId());
            Query query = new Query(Criteria.where("customer.id").is(customer.getId()));
            Update update = new Update().set("customer", customer);
            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        }catch (Exception e){
            SyncLogger.error("Error updating appointment customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateBeautyProcedure(BeautyProcedureDTO beautyProcedureDTO) {
        try{
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedureDTO.getId());
            Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedureDTO.getId()));
            Update update = new Update()
                    .set("beautyProcedure.name", beautyProcedureDTO.getName())
                    .set("beautyProcedure.description", beautyProcedureDTO.getDescription());
            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        }catch (Exception e){
            SyncLogger.error("Error updating appointment beauty procedure: "+ e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }


    }
}