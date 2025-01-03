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
        try {

            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {

        try {

            SyncLogger.info("Update appointment customer: " + customer.getId());
            var queryCustomer = new Query(Criteria.where("customer_id").is(customer.getId()));
            var updateCustomer = new Update().set("customer", customer);
            mongoTemplate.updateMulti(queryCustomer, updateCustomer, FullAppointmentDTO.class);

        } catch (Exception e) {
            SyncLogger.error("Error update appointment customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateBeautyProcedure(BeautyProcedureDTO beautyProcedure) {

        try {

            SyncLogger.info("Update appointment beauty procedure: " + beautyProcedure.getId());
            var query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedure.getId()));
            var update = new Update()
                    .set("beautyProcedure.name", beautyProcedure.getName())
                    .set("beautyProcedure.description", beautyProcedure.getDescription());

            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);

        } catch (Exception e) {
            SyncLogger.error("Error update appointment beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
